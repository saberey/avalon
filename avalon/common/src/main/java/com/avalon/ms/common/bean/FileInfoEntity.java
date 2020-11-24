package com.avalon.ms.common.bean;

import lombok.Data;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年1月29日 上午10:09:30
 *@version
 */
@Data
public class FileInfoEntity {

	private String localFile;
	private String remotePath;
	private String remoteFileName;
	private int uploadOrDownload;
	private int status;
	
	public FileInfoEntity(String localFile,String remoteFileName,String remotePath,int uploadOrDownload,int status){
		this.localFile = localFile;
		this.remoteFileName = remoteFileName;
		this.remotePath = remotePath;
		this.uploadOrDownload = uploadOrDownload;
		this.status = status;
	}
}
