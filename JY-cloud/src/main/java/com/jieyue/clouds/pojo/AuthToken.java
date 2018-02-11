package com.jieyue.clouds.pojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.jieyue.clouds.pojo.Identity;
import com.jieyue.clouds.pojo.Scope;
import com.jieyue.clouds.utils.JsonUtil;
public class AuthToken {
	
	public static String token(String url){
		    String params = getAuth();
			StringEntity entity = new StringEntity(params, HTTP.UTF_8);
			entity.setContentType("application/json");
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			String token = null;
			try {
				HttpResponse response = client.execute(httpPost);
				Header[] headers = response.getAllHeaders();
				for (int i = 0; i < headers.length; i++) {
					if("X-Subject-Token".equals(headers[i].getName())){
						return headers[i].getValue();
					}
				}
				return token;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return token;
		}

	public static String getAuth(){

		Project pro = new Project("f4dc1ed0c7d743fbbdfcd3003c45a497");
	    User user = new User("952c3b6e370d4f9694b9d97e88b7ed36","supersecret");	
	    Password pwd = new Password();
	    Identity id = new Identity();
	    String[] methodsStr = new String[]{"password"};
	    id.setMethods(methodsStr);
	    pwd.setUser(user);
	    id.setPassword(pwd);
	    
	    Scope scope = new Scope();
	    scope.setProject(pro);
	    
	    Auth auth = new Auth();
	    auth.setIdentity(id);
	    auth.setScope(scope);
	    
	    Map<String,Object> map = new HashMap<String, Object>();
		map.put("auth", auth);
	    return JsonUtil.toJson(map);
	}
	
}
