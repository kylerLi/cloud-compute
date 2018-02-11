package com.jieyue.clouds.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.jieyue.clouds.pojo.AuthToken;
import com.jieyue.clouds.pojo.RestHttpResponse;
import com.jieyue.clouds.utils.RestHttpClient;

public class ComputeServer {

	public static RestHttpResponse createServer(String url,String serverParams,String token){
		return RestHttpClient.post(url, serverParams, token);
	}
	
	public static RestHttpResponse deleteServer(String url,String token){
		return RestHttpClient.delete(url,token);
	}
	
	public static RestHttpResponse getServers(String url, String token){
		return RestHttpClient.get(url,token);
	}
}
