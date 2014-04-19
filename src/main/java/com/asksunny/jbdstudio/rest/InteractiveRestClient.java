package com.asksunny.jbdstudio.rest;

import java.util.Collections;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class InteractiveRestClient {

	
	public final static String PROMPT = "restclient>";
	
	public void invoke()
	{
		ResteasyClientBuilder clientBuilder = new ResteasyClientBuilder();
		
		//clientBuilder.defaultProxy(hostname, port, scheme)
		//clientBuilder.disableTrustManager()
		//clientBuilder.keyStore(keyStore, password)
		//clientBuilder.trustStore(truststore)
		
		
		ResteasyClient client = clientBuilder.build();	
		ResteasyWebTarget target = client.target("http://localhost:12345/jbdstudio/ping");
		Response response = target.request().get(); //.accept(MediaType.WILDCARD).post(arg0)
		
		
		MultivaluedHashMap<String, String> params = new MultivaluedHashMap<String, String>();
		params.put("name", Collections.singletonList("value"));
		//target.request().accept(MediaType.WILDCARD).post(Entity.form(new Form(params)));
		//target.request().accept(MediaType.WILDCARD).put(Entity.form(new Form(params)));
		
		String ret = response.readEntity(String.class);
		System.out.println(ret);
		response.close();
		client.close();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
	}

}
