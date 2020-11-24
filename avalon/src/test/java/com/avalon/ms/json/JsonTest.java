package com.avalon.ms.json;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

/**
 *@descriptionTODO
 *@author saber
 *@date 2018年2月28日 下午3:16:44
 *@version
 */
public class JsonTest {
	
	public static void main(String[] args) {
		
		/**
		 * {"requestNo":"CDMS1506577880511","fileDate":"20170510","detail":[{"fileType":"RECHARGE"},{"fileType":"TRANSACTION"},{"fileType":"WITHDRAW"},{"fileType":"COMMISSION"},{"fileType":"BACKROLL_RECHARGE"}],"timestamp":"20170928135140”}对账确认的请求报文
		 */
		Map<String,Object> reqData = new HashMap<>();
		reqData.put("requestNo", "CDMS1506577880511");
		reqData.put("fileDate", "20170510");
		FileType[] fileTypeData = new FileType[2];
		fileTypeData[0] = new FileType("RECHARGE");
		fileTypeData[1] = new FileType("TRANSACTION");
		reqData.put("detail", fileTypeData);
		reqData.put("timestamp", "20170928135140");
		
		System.out.println(JSON.toJSONString(reqData));
		System.out.println(UUID.randomUUID().toString());
		
	}
	static class FileType{
		String fileType;
		public FileType(String fileType){
			this.fileType = fileType;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
	}
}
