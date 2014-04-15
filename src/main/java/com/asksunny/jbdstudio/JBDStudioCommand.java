package com.asksunny.jbdstudio;

public interface JBDStudioCommand 
{
	public void init(JBDStudioContext context);
	
	public void execute();
}
