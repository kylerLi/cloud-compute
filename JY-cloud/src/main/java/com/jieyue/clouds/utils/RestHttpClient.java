package com.jieyue.clouds.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.jieyue.clouds.pojo.RestHttpResponse;

public class RestHttpClient {
	
	public static RestHttpResponse post(String url,String serverParams, String token){
		RestHttpResponse result = new RestHttpResponse();
		
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(serverParams, HTTP.UTF_8);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		httpPost.setHeader("X-Auth-Token", token);
		HttpClient client = new DefaultHttpClient();
	    try {
			HttpResponse response = client.execute(httpPost);
			
			result.setStatusCode(response.getStatusLine().getStatusCode());
			
			InputStream ins = response.getEntity().getContent();
			StringWriter writer = new StringWriter();
			IOUtils.copy(ins, writer, "UTF-8");
			result.setContent(writer.toString());
			System.out.println(result.getStatusCode());
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static RestHttpResponse get(String url,String token){
		
		RestHttpResponse result = new RestHttpResponse();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Content-Type", "application/json; charset=" + HTTP.UTF_8);
		httpGet.setHeader("X-Auth-Token", token);
		HttpClient client = new DefaultHttpClient();
		
		try {
			HttpResponse response = client.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			
			result.setStatusCode(response.getStatusLine().getStatusCode());
			InputStream ins = response.getEntity().getContent();
			StringWriter writer = new StringWriter();
			IOUtils.copy(ins, writer, "UTF-8");
			result.setContent(writer.toString());
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
public static RestHttpResponse delete(String url,String token){
		
		RestHttpResponse result = new RestHttpResponse();
		
		HttpDelete httpDelete = new HttpDelete(url);
		httpDelete.addHeader("Content-Type", "application/json; charset=" + HTTP.UTF_8);
		httpDelete.setHeader("X-Auth-Token", token);
		HttpClient client = new DefaultHttpClient();
		
		try {
			HttpResponse response = client.execute(httpDelete);
			int statusCode = response.getStatusLine().getStatusCode();
			
			result.setStatusCode(response.getStatusLine().getStatusCode());
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

}
