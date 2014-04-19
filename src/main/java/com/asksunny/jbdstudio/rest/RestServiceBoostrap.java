package com.asksunny.jbdstudio.rest;

import java.util.Collections;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import com.asksunny.jbdstudio.JBDStudioConfiguration;
import com.asksunny.jbdstudio.util.CLIOptions;

public class RestServiceBoostrap {

	JBDStudioConfiguration configuration;

	public RestServiceBoostrap(JBDStudioConfiguration configuration) {
		super();
		this.configuration = configuration;
	}

	public void run() {
		
		ResteasyDeployment deployment = new ResteasyDeployment();
		deployment.setResourceClasses(Collections
				.singletonList(JBDStudioRestService.class.getName()));
		final NettyJaxrsServer server = new NettyJaxrsServer();
		server.setDeployment(deployment);
		// SSLContext ssl = SSLContext.getInstance("TLS");
		// ssl.init(kms, tms, null);
		int port = configuration.getOptions().getIntOption("port");
		if(port==0)  port = 12345;
		server.setPort(port);
		server.start();
		System.out.println("JBDStudioRestService Server listening on port " + port);
	}

	public static void main(String[] args) {
		CLIOptions options = new CLIOptions(args);
		JBDStudioConfiguration cfg = new JBDStudioConfiguration(options);
		RestServiceBoostrap boostra = new RestServiceBoostrap(cfg);
		boostra.run();
	}

}
