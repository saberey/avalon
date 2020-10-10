package com.avalon.ms.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestRquestUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RestRquestUtil.class);

	/**
	 * 获取状态码
	 * 
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public static int getPostStatus(String url) {
		int status = 0;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		String jsonText = null;
		try {
			status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return status;
	}
	/**
	 * 获取状态码
	 * 
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public static int getPostStatusByHttps(String url) {
		int status = 0;
		HttpClient client = new HttpClient();
	    Protocol myhttps = new Protocol("https", new SelfSSLProtocolSocketFactory(), 443);   
	    Protocol.registerProtocol("https", myhttps); 
	    
		PostMethod postMethod = new PostMethod(url);
		String jsonText = null;
		try {
			status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return status;
	}

	
	/**
	 * 获取状态码
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static int  getPostStatusByHttps(String url, Map<String, String> map) {
		int status = 0;
		logger.info("url:" + url);
		NameValuePair[] data = map2NameValue(map);
		String jsonText = null;
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(data);
		try {
			status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return status;
	}
	/**
	 * 获取状态码
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static int  getPostBodyJsonStatusByHttps(String url, Map<String, String> map) throws UnsupportedEncodingException {
		int status = 0;
		String data = JSONObject.toJSONString(map);
		logger.info("url:" + url+"  param:"+data);
		String jsonText = null;
		HttpClient client = new HttpClient();		
		PostMethod postMethod = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(data,"text/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return status;
	}
	/**
	 * 获取返回值
	 * 
	 * @param url
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String  getPostBodyJsonResponseByHttps(String url, Map<String, String> map) throws UnsupportedEncodingException {
		int status = 0;
		String data = JSONObject.toJSONString(map);
		logger.info("url:" + url+"  param:"+data);
		String jsonText = null;
		HttpClient client = new HttpClient();		
		PostMethod postMethod = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(data,"text/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return jsonText;
	}
	
	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static Object[] getPostDataByHttps(String url, Map<String, String> map,boolean isStream,String charSet) {
		logger.info("url:" + url);
		NameValuePair[] data = map2NameValue(map);
		String jsonText = null;
		byte[] responseStream=null;
		HttpClient client = new HttpClient();
		if(charSet!=null)
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");		
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(data);
		try {
			int status = client.executeMethod(postMethod);
			if(isStream){	
				responseStream=postMethod.getResponseBody();
				objArray[1] = responseStream;
			}
			else{
				jsonText = postMethod.getResponseBodyAsString();
				objArray[1] = jsonText;
			}
			logger.info("response:" + jsonText);
			objArray[0] = status;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}
	
	
	
	/**
	 * 获取返回信息
	 * 
	 * @param url
	 * @return
	 */
	public static String getPostText(String url) {
		String jsonText = null;
		HttpClient client = new HttpClient();

		PostMethod postMethod = new PostMethod(url);
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return jsonText;
	}
	/**
	 * 获取返回信息
	 * 
	 * @param url
	 * @return
	 */
	public static String getGetText(String url) {
		String jsonText = null;
		HttpClient client = new HttpClient();
		
		GetMethod getMethod = new GetMethod(url);
		try {
			int status = client.executeMethod(getMethod);
			jsonText = getMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}finally{		
			getMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return jsonText;
	}
	/**
	 * 获取返回信息
	 * 
	 * @param url
	 * @return
	 */
	public static String getGetTextByHttps(String url) {
		String jsonText = null;
		HttpClient client = new HttpClient();
	    Protocol myhttps = new Protocol("https", new SelfSSLProtocolSocketFactory(), 443);   
	    Protocol.registerProtocol("https", myhttps);   
	    
		GetMethod getMethod = new GetMethod(url);
		try {
			int status = client.executeMethod(getMethod);
			jsonText = getMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}finally{		
			getMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return jsonText;
	}

	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @return
	 */
	public static Object[] getPostData(String url) {
		logger.info("url:" + url);
		String jsonText = null;
		HttpClient client = new HttpClient();
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
			objArray[0] = status;
			objArray[1] = jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static Object[] getPostDataByHttps(String url){
		return getPostDataByHttps(url,null);
	}
	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @return
	 */
	public static Object[] getPostDataByHttps(String url,Map<String,String> param) {
		logger.info("url:" + url);
		String jsonText = null;
		HttpClient client = new HttpClient();
	    Protocol myhttps = new Protocol("https", new SelfSSLProtocolSocketFactory(), 443);   
	    Protocol.registerProtocol("https", myhttps);   
	    
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		if(param!=null){
			NameValuePair[] data = map2NameValue(param);
			postMethod.setRequestBody(data);
		}
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
			objArray[0] = status;
			objArray[1] = jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}
	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Object[] getPostDataByHttpsJsonParam(String url,Map<?,?> map) throws UnsupportedEncodingException {
		String data = JSONObject.toJSONString(map);
		logger.info("url:" + url+"  param:"+data);
		String jsonText = null;
		HttpClient client = new HttpClient();
		Protocol myhttps = new Protocol("https", new SelfSSLProtocolSocketFactory(), 443);   
		Protocol.registerProtocol("https", myhttps);		
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(data,"text/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
			objArray[0] = status;
			objArray[1] = jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}
	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Object[] getPostDataByHttpJsonParam(String url,Map<?,?> map) throws UnsupportedEncodingException {
		String data = JSONObject.toJSONString(map);
		logger.info("url:" + url+"  param:"+data);
		String jsonText = null;
		HttpClient client = new HttpClient();
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		RequestEntity requestEntity = new StringRequestEntity(data,"text/json","UTF-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
			objArray[0] = status;
			objArray[1] = jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}

	public static NameValuePair[] map2NameValue(Map<String, String> map) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (map == null) {
			return null;
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			list.add(new NameValuePair(entry.getKey(), entry.getValue()));
		}
		
		return list.toArray(new NameValuePair[]{});
	}

	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static Object[] getPostData(String url, Map<String, String> map) {
		return getPostData(url, map, null);
	}
	
	/**
	 * 返回状态及body信息
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public static Object[] getPostData(String url, Map<String, String> map, String context_charset) {
		logger.info("url:" + url);
		NameValuePair[] data = map2NameValue(map);
		String jsonText = null;
		HttpClient client = new HttpClient();
		Object[] objArray = new Object[2];
		PostMethod postMethod = new PostMethod(url);
		if(context_charset != null){
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, context_charset);		
		}
		postMethod.setRequestBody(data);
		try {
			int status = client.executeMethod(postMethod);
			jsonText = postMethod.getResponseBodyAsString();
			logger.info("response:" + jsonText);
			objArray[0] = status;
			objArray[1] = jsonText;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{		
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).shutdown(); 
		}
		return objArray;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(RestRquestUtil.getPostStatusByHttps("https://dubbo-billing.local-kaiyuan.com/getStagingBillinfo/"));
		;
	}
}
