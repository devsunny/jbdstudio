package com.asksunny.jbdstudio;

public interface JBDStudioServerService extends Runnable, JBDStudioService
{
	public String getName();
	public void init(JBDStudioContext context);
}
