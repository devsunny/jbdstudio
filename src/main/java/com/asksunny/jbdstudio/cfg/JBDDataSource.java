package com.asksunny.jbdstudio.cfg;

import org.apache.commons.dbcp.BasicDataSource;

public class JBDDataSource extends BasicDataSource {

	String name = "default";
	
	

	public JBDDataSource() {
		super();		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
