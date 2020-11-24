package com.avalon.ms.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient tools
 * @descriptionTODO
 * @author saber
 * @time: 2017年8月2日 下午3:45:53
 * @version
 */
public class HttpClientUtil {
	
	static{
		init();
	}
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	/**
	 *  http get
	 */
	public static Object[] httpGet(String uri){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(uri);
		logger.info("http get request :"+get.getRequestLine());
		CloseableHttpResponse response = null;
		HttpEntity entity= null;
		Object[] result = new Object[2];
		try {
		    response = httpClient.execute(get);
			int status = response.getStatusLine().getStatusCode();
			result[0] = status;
			// 200 - 300
		//	if(status>=HttpStatus.SC_OK && status <HttpStatus.SC_MULTIPLE_CHOICES){
		//		if(status == HttpStatus.SC_OK ){
				    entity = response.getEntity();
			//		return EntityUtils.toByteArray(entity);
			//	}
		//	}
				    result[1] = EntityUtils.toByteArray(entity);
				    return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("http get request protocol error!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(null != entity) EntityUtils.consume(entity);
				if(null != response) response.close();
				if(null != httpClient) httpClient.close();
				logger.info("httpclient close!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static Object[] httpGet(String uri, String params){
		return httpGet(uri+"?"+params);
	}
	
	
	private static HttpClient client;
	
	public static void init(){
		client = HttpClients.createDefault();
	}
	
	public static Object[] post(String uri,Map<String,String> nameValueMap){
		//init();
		HttpPost post = new HttpPost(uri);
		//RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
	//	post.setConfig(requestConfig);
		logger.info("http post request :"+post.getRequestLine());
		if(null != nameValueMap){
			try {
				post.setEntity(new UrlEncodedFormEntity(map2NameValuePair(nameValueMap)));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CloseableHttpResponse response = null;
		HttpEntity entity= null;
		Object[] result = new Object[2];
		try {
		    response = (CloseableHttpResponse) client.execute(post);
			int status = response.getStatusLine().getStatusCode();
			result[0] = status;
			// 200 - 300
			//if(status>=HttpStatus.SC_OK && status <HttpStatus.SC_MULTIPLE_CHOICES){
		//		if(status == HttpStatus.SC_OK ){
				    entity = response.getEntity();
			//		return EntityUtils.toByteArray(entity);
			//	}
			//}
			 result[1] = EntityUtils.toByteArray(entity);
			 return result;
			 
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("http get request protocol error!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(null != entity) EntityUtils.consume(entity);
				//if(null != response) response.close();
				//if(null != client)  client.
				logger.info("httpclient close!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	/**
	 * http post
	 */
	public static Object[] httpPost(String uri,Map<String,String> nameValueMap){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
		post.setConfig(requestConfig);
		logger.info("http post request :"+post.getRequestLine());
		if(null != nameValueMap){
			try {
				post.setEntity(new UrlEncodedFormEntity(map2NameValuePair(nameValueMap)));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CloseableHttpResponse response = null;
		HttpEntity entity= null;
		Object[] result = new Object[2];
		try {
		    response = httpClient.execute(post);
			int status = response.getStatusLine().getStatusCode();
			result[0] = status;
			// 200 - 300
			//if(status>=HttpStatus.SC_OK && status <HttpStatus.SC_MULTIPLE_CHOICES){
		//		if(status == HttpStatus.SC_OK ){
				    entity = response.getEntity();
			//		return EntityUtils.toByteArray(entity);
			//	}
			//}
			 result[1] = EntityUtils.toByteArray(entity);
			 return result;
			 
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			logger.error("http get request protocol error!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(null != entity) EntityUtils.consume(entity);
				if(null != response) response.close();
				if(null != httpClient) httpClient.close();
				logger.info("httpclient close!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public static List<NameValuePair> map2NameValuePair(Map<String,String> nameValueMap){
		if(null == nameValueMap||nameValueMap.size()==0){
			return null;
		}
		List<NameValuePair> nvpl = new ArrayList<NameValuePair>(nameValueMap.size());
		for(Map.Entry<String, String> entry : nameValueMap.entrySet()){
			nvpl.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return nvpl;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		String uri="http://localhost:8080/ms/city/code?city=1";
		//Map<String >
		for(int i=0;i<10;i++){
			Object[] result = HttpClientUtil.post(uri, null);
			System.out.println(result[0]);
			System.out.println(new String((byte[])result[1]));
			TimeUnit.SECONDS.sleep(2);
		}
	}
}
