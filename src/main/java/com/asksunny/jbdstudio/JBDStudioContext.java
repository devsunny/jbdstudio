package com.asksunny.jbdstudio;

import java.util.HashMap;

import com.asksunny.jbdstudio.util.CLIOptions;

public class JBDStudioContext extends HashMap<String, Object>
{

	
	JBDStudionConfiguration configuration;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JBDStudioContext(CLIOptions options)
	{
		configuration = new JBDStudionConfiguration(options);
	}

	public JBDStudionConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(JBDStudionConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	
	
	

}
