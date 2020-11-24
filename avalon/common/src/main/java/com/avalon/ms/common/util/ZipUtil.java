package com.avalon.ms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月29日 上午11:42:41
 *@version
 */
public class ZipUtil {

	private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);
    private static final int BUFFER = 8192;

    /**
     * 解压缩
     * @param src
     * @param dest
     */
    public static void decompress(String src,String dest){

        File file = new File(src);
        if(!file.exists()){
            throw new RuntimeException(src+"文件不存在");
        }
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration entries = zipFile.entries();
            ZipEntry zipEntry = null;
            while(entries.hasMoreElements()){
                zipEntry = (ZipEntry) entries.nextElement();
                if(zipEntry.isDirectory()){
                    String dirPath = dest+File.separator+zipEntry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                }else{
                    // 表示文件
                    File f = new File(dest + File.separator + zipEntry.getName());
                    if (!f.exists()) {
                        File parentDir = new File(dest);
                        if(!parentDir.exists()) {
                            parentDir.mkdirs();
                        }
                    }
                    f.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(zipEntry);
                    FileOutputStream fos = new FileOutputStream(f);
                    int count;
                    byte[] buf = new byte[BUFFER];
                    while ((count = is.read(buf)) != -1) {
                        fos.write(buf, 0, count);
                    }
                    is.close();
                    fos.close();
                }
            }
        }catch(IOException e){
            logger.error("解压文件{}出错|{}",src,e);
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件
     * @param file
     * @throws IOException
     */
    public static void compressFile(String file) throws IOException{
        File src = new File(file);
        if(!src.exists()){
            return;
        }
        File zip = new File(file+".zip");
        FileOutputStream fos = new FileOutputStream(zip);
        CheckedOutputStream cos = new CheckedOutputStream(fos,new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        compressFile(src,zos);
        zos.close();
        logger.info("压缩文件{}成功!",file);
    }

    /**
     * 压缩文件
     * @param file
     * @param zos
     */
    public static void compressFile(File file, ZipOutputStream zos){
        if(!file.exists()){
            return;
        }
        try{
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            int count =0;
            byte[] data  = new byte[BUFFER];
            while((count = bis.read(data,0,BUFFER))!=-1){
                zos.write(data,0,count);
            }
            bis.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 压缩文件夹
     * @param path
     * @throws IOException
     */
    public static void compressPath(String path) throws IOException {
        File dir = new File(path);
        if(!dir.exists()){
            return;
        }
        File zip = new File(path+".zip");
        FileOutputStream fos = new FileOutputStream(zip);
        CheckedOutputStream cos = new CheckedOutputStream(fos,new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);

        File[] files = dir.listFiles();
        for (int i=0;i<files.length;i++){
            compressFile(files[i],zos);
        }
        zos.close();
        logger.info("压缩文件夹{}成功!",path);
    }
}
