package com.jieyue.clouds;

import org.junit.*;

import com.jieyue.clouds.pojo.AuthToken;
import com.jieyue.clouds.pojo.RestHttpResponse;
import com.jieyue.clouds.service.ComputeServer;

public class CreateServerTest {
	
    @Test
    public void create(){
    	String url = "http://192.168.64.29/identity/v3/auth/tokens";
		String token = AuthToken.token(url);
		
		String createserverUrl = "http://192.168.64.29/compute/v2.1/servers";
		String serverParams = "{\n  \"server\": {\n    \"name\": \"lifazhao-inst2\",\n    \"imageRef\": \"c095bd35-798f-4d82-942a-d2fa2d1f1e43\",\n    \"flavorRef\": \"1\",\n    \"max_count\": 1,\n    \"min_count\": 1,\n    \"networks\": [\n      {\n        \"uuid\": \"16d268b3-e629-439d-9cb3-46f9d713724b\"\n      }\n    ],\n    \"security_groups\": [\n      {\n        \"name\": \"default\"\n      }\n    ]\n  }\n}";
		ComputeServer.createServer(createserverUrl,serverParams,token);
    }
    
    @Test
    public void list(){
    	String url = "http://192.168.64.29/identity/v3/auth/tokens";
		String token = AuthToken.token(url);
		
		String serverUrl = "http://192.168.64.29/compute/v2.1/servers";
		
		RestHttpResponse response = ComputeServer.getServers(serverUrl, token);
		System.out.println(response.getContent());
    }
    
    @Test
    public void delete(){
    	String url = "http://192.168.64.29/identity/v3/auth/tokens";
		String token = AuthToken.token(url);
		
		String serviceSid = "e2bec80f-79a5-423e-b8f2-74931aecd794";
		String delurl = "http://192.168.64.29/compute/v2.1/servers/" + serviceSid;
		RestHttpResponse response = ComputeServer.deleteServer(delurl,token);
		System.out.println(response.getStatusCode());
    }
}
