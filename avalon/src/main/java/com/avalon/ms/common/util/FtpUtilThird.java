package com.avalon.ms.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.avalon.ms.common.bean.FileInfoEntity;
import com.avalon.ms.contants.Constants;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月26日 下午2:10:14
 *@version
 */
public class FtpUtilThird {

	private  final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    private   FTPClient ftpClient = null;

    /**
     * 登陆ftp服务器
     * @return
     */
    public  boolean login(String host,int port,String username,String passwd){
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(host,port);
            boolean result = ftpClient.login(username,passwd);
            if(!result){
                logger.error("ftp:host:{}|{}",host,result?"登陆成功":"登陆失败");
                return false;
            }
            int reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                logger.error("服务器拒绝连接{}",reply);
                return false;
            }
            logger.info("ftp:host:{}|{}登陆成功",host,result);
            return true;
        } catch (IOException e) {
            logger.info("ftp:host:{}|登陆失败|{}",host,e);
            return false;
        }
    }

    /**
     * 上传文件
     * @param localFile
     * @param remoteFileName
     * @param remotePath
     * @return
     */
    public  boolean upLoad(String localFile,String remoteFileName,String remotePath,CallBack callBack){
        return upload(localFile,remoteFileName,remotePath,callBack,false);
    }

    /**
     * 上传文件 启用被动模式
     * @param localFile
     * @param remoteFileName
     * @param remotePath
     * @return
     */
    public  boolean upLoadPasv(String localFile,String remoteFileName,String remotePath,CallBack callBack){
        return upload(localFile,remoteFileName,remotePath,callBack,true);
    }

    /**
     * 上传文件
     * @param fis
     * @param remotePath
     * @param remoteFileName
     * @return
     */
    public  boolean upload(FileInputStream fis,String localFile,String remotePath,String remoteFileName,CallBack callBack){
            if(StringUtils.isBlank(remoteFileName))
                throw new RuntimeException("上传文件需填写文件名！");

            if(!changePath(remotePath)){
                throw new RuntimeException("切入目录失败！"+remotePath);
            }
            boolean result = false;
        try{
            ftpClient.setBufferSize(1024);
           //ftpClient.setControlEncoding(Constants.UTF_8);
           // FTPClientConfig ftpClientConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
           // ftpClientConfig.setServerLanguageCode(Constants.LANGUAGE_ZH);
           // ftpClient.configure(ftpClientConfig);
            //ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if(ftpClient.storeFile(remoteFileName,fis)){
                fis.close();
                logger.info("目标路径{}上传文件{}成功！",remotePath,remoteFileName);
                result = true;
                return true;
            }
            logger.error("目标路径{}上传文件{}失败",remotePath,remoteFileName);
            return false;
        } catch (IOException e) {
            logger.error("ftp上传文件异常,{}",e);
            e.printStackTrace();
            return false;
        } finally{
            if(callBack!=null){
                callBack.proceed(new FileInfoEntity(localFile,remoteFileName,remotePath,Constants.UPLOAD,result?1:2));
            }
        }
    }

    /**
     * 上传文件
     * @param localFile
     * @param remoteFileName
     * @param remotePath
     * @param pasv
     * @return
     */
    public  boolean upload(String localFile,String remoteFileName,String remotePath,CallBack callBack,boolean  pasv){
        if(null==ftpClient){
            logger.error("ftp创建连接失败!");
            return false;
        }
        if(!ftpClient.isConnected()){
            logger.error("ftp连接断开！");
            return false;
        }
        if(StringUtils.isBlank(remoteFileName))
            throw new RuntimeException("上传目标文件必须填写文件名！");
        File file = new File(localFile);
        if(!file.exists()){
            throw new RuntimeException("上传本地文件不存在！");
        }
        try {
            if(pasv){
                //ftpClient.enterRemotePassiveMode();
                 ftpClient.enterLocalPassiveMode();
                // ftpClient.enterRemotePassiveMode();
                //ftpClient.pasv();
            }
            FileInputStream fis = new FileInputStream(file);
            boolean result  = upload(fis,localFile,remotePath,remoteFileName,callBack);
            fis.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 切换目录，没有则创建
     * @param remotePath
     * @return
     */
    public boolean changePath(String remotePath){
        if(StringUtils.isBlank(remotePath)){
            return false;
        }
        String path;
        try {
            if (ftpClient.changeWorkingDirectory(remotePath))
                return true;
            remotePath = StringHelper.trimEnd(remotePath,"/");
            remotePath = StringHelper.trimEnd(remotePath,"/");
            String[] strs = remotePath.split("/");
            StringBuffer stringBuffer = new StringBuffer();
            for(String s: strs){
                stringBuffer.append("/");
                stringBuffer.append(s);
                if(ftpClient.changeWorkingDirectory(stringBuffer.toString())){
                    continue;
                }
                if(!ftpClient.makeDirectory(stringBuffer.toString())){
                    logger.error("创建目录{}失败",stringBuffer.toString());
                    return false;
                }
            }
            logger.info("目录{}切换成功",stringBuffer.toString());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public  boolean download(String remoteFileName,String remotePath,String localFile,CallBack callBack){
        return download(remoteFileName, remotePath,localFile,callBack,false);
    }

    /**
     * 启用被动模式下载
     * @param remoteFileName
     * @param remotePath
     * @param localFile
     * @return
     */
    public  boolean downloadPasv(String remoteFileName, String remotePath, String localFile, CallBack callBack){
        return download(remoteFileName, remotePath,localFile,callBack,true);
    }

    /**
     * 下载文件
     * @param remoteFileName
     * @param remotePath
     * @param localFile
     * @param pasv
     * @return
     */
    public  boolean download(String remoteFileName,String remotePath,String localFile,CallBack callBack,boolean pasv){
        if("".equals(remotePath))
            remotePath="/";
        boolean result = false;
        try {
            if(null==ftpClient){
                 logger.error("创建连接失败!");
                return false;
            }
            if(pasv) {
                ftpClient.enterLocalPassiveMode();
            }
           // String dir = new String(remotePath.getBytes(CHARSET_LOCAL), CHARSET_REMOTE); //
            if(!ftpClient.changeWorkingDirectory(remotePath)){
                logger.error("切换目录{}出错",remotePath);
                return false;
            }
            //ftpClient.setControlEncoding("gbk");
            //ftpClient.setFileTransferMode(FTPClient.ASCII_FILE_TYPE);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile cur : ftpFiles){
                if(cur.getName().equals(remoteFileName)){

                    //判断文件夹是否存在
                    File pathDir = new File(localFile);
                    if(!pathDir.exists()){
                        pathDir.mkdirs();
                    }

                    //文件不存在，创建
                    File file = new File(pathDir, remoteFileName);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    //将文件内容写到本地
                    FileOutputStream fos = new FileOutputStream(file);
                    ftpClient.retrieveFile(cur.getName(),fos);
                    fos.close();
                    logger.info("文件{}已下载",cur.getName());
                    result = true;
                    return true;
                }
            }
            logger.error("下载文件路径{}下文件{}失败",remotePath,remoteFileName);
            return false;
        }catch(Exception e){
            logger.error("ftp download error,{}",e);
            e.printStackTrace();
            return false;
        }finally{
            if(callBack!=null){
                callBack.proceed(new FileInfoEntity(localFile,remoteFileName,remotePath,Constants.DOWNLOAD,result?2:1));
            }
        }
    }

    /**
     *
     * @param remotePath
     * @param key
     * @return
     */
    public List<String> listFiles(String remotePath, String key){
        if("".equals(remotePath))
            remotePath="/";
        try {
            if (null == ftpClient) {
                logger.error("创建连接失败!");
                return null;
            }
            if (!ftpClient.changeWorkingDirectory(remotePath)) {
                logger.error("切换目录{}出错", remotePath);
                return null;
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            List<String> fileList = new ArrayList<>();
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile cur : ftpFiles){
                if(cur.getName().contains(key)){
                    fileList.add(cur.getName());
                }
            }
            return fileList;
        }catch(IOException e){
            e.printStackTrace();
            logger.error("获取ftp指定目录文件列表失败|{}",e);
            return null;
        }
    }

    /**
     * 删除ftp服务器文件
     * @param target
     * @return
     */
    public boolean removeFile(String target){
        boolean result = false;
        if(StringUtils.isBlank(target)){
            logger.error("待删除文件名为空");
            return result;
        }
        try {
            result = ftpClient.deleteFile(target);
            logger.info("删除文件{}|{}",target,result?"成功":"失败");
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *  关闭连接
     */
    public void close(){
        if(ftpClient!=null){
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("ftp 关闭连接出错{}",e);
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        FtpUtilThird ftpUtil = new FtpUtilThird();
        ftpUtil.login("106.39.84.163",8009,"qykj","PKdpfIrzY9eOaoNR");
        //ftpUtil.upload("e:/stu.js","stu.js","/depository",true);
        ftpUtil.downloadPasv("P00002_HK_20180125_0001.zip","/depository","e:/P00002_HK_20180125_0001.zip",null);
        ftpUtil.close();
    }
}
