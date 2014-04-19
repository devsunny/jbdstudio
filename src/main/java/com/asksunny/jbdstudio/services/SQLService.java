package com.asksunny.jbdstudio.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/json/sql")
public class SQLService {


	@GET
	@Path("/get")
	@Produces("application/json")
	public SQLResponse getMovieInJSON() { 
		SQLResponse response = new SQLResponse();
		response.setResult("Helloollllllllllllllllllllll");
		response.setId(123566776); 
		return response;  
	}
}
