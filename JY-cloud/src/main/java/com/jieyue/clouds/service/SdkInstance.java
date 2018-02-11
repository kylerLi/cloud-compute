package com.jieyue.clouds.service;

import java.util.Properties;

import javax.swing.JComboBox.KeySelectionManager;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.http.okhttp.config.OkHttpCommandExecutorServiceModule;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.keystone.v2_0.KeystoneApi;
import org.jclouds.openstack.keystone.v2_0.config.KeystoneProperties;
import org.jclouds.openstack.keystone.v2_0.features.TokenApi;
import org.jclouds.sshj.config.SshjSshClientModule;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

import javafx.scene.control.ContextMenuBuilder;


public class SdkInstance {
	
	public static void main(String[] args) {
		
		Properties overrides = new Properties();
		// Project scoped authorization (can use the project name or the ID)
//		overrides.put(KeystoneProperties.SCOPE, "projectId:2f9b30f706bc45d7923e055567be2e98");
//		Domain scoped authorization (can use the domain name or the ID)
//		overrides.put(KeystoneProperties.SCOPE, "domain:default");
//		overrides.put(KeystoneProperties.SCOPE, "domainId:2f9b30f706bc45d7923e055567be2e98");
		overrides.put("jclouds.keystone.version", "2");
		
		/*KeystoneApi keystone = ContextBuilder.newBuilder("openstack-keystone")
				   .endpoint("http://192.168.64.29/identity/v2")
				   .credentials("domain:demo", "supersecret")
				   .overrides(overrides)
				   .modules(ImmutableSet.of(new SLF4JLoggingModule(), new OkHttpCommandExecutorServiceModule())) // use OkHttp driver
				   .buildApi(KeystoneApi.class);
		System.out.println(keystone);*/
		
		ComputeServiceContext context = ContextBuilder.newBuilder("openstack-nova")
				.endpoint("http://192.168.64.29/compute/v2.1")
                .credentials("demo:demo", "supersecret")
                .modules(ImmutableSet.<Module> of(new SLF4JLoggingModule(),
                                                  new SshjSshClientModule()))
                .buildView(ComputeServiceContext.class);

		ComputeService client = context.getComputeService();
		for (ComputeMetadata node : client.listNodes()) {
			   node.getId(); // how does jclouds address this in a global scope
			   node.getProviderId(); // how does the provider api address this in a specific scope
			   node.getName(); // if the node is named, what is it?
			   node.getLocation(); // where in the world is the node
			   System.out.println(node.getName());
			}
		
		
		
	}

}
