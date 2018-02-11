package com.jieyue.clouds.pojo;

import org.jclouds.cloudstack.domain.Project;

public class RestHttpResponse {
	private int statusCode;
	private String content;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
