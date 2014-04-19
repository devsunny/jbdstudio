package com.asksunny.jbdstudio.services;

import java.util.Collections;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

public class RestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		    ResteasyDeployment deployment = new ResteasyDeployment();
	        deployment.setResourceClasses(Collections.singletonList(SQLService.class.getName()));
	        final NettyJaxrsServer server = new NettyJaxrsServer();
	        //SSLContext ssl = SSLContext.getInstance("TLS");
	        //ssl.init(kms, tms, null);	        
	        int port = 3000;
	        server.setPort(port);
	        server.start();
	        System.out.println("Server listening on port "+port);
	}

}
