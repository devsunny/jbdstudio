package com.asksunny.jbdstudio.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/jbdstudio")
public class JBDStudioRestService {

	
	@GET
	@Path("/ping")
	@Produces({MediaType.TEXT_PLAIN})
	public String ping()
	{
		return (new Date()).toString();
	}
}
