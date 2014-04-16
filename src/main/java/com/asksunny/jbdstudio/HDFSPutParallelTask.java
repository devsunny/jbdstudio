package com.asksunny.jbdstudio;

import java.io.File;

public class HDFSPutParallelTask implements JBDStudioParallelTask {

	String localDir; 
	String remoteDir; 
	int maxRetry;
	
	
	public HDFSPutParallelTask(String localDir, String remoteDir, int maxRetry) {
		super();
		this.localDir = localDir;
		this.remoteDir = remoteDir;
		this.maxRetry = maxRetry;
	}




	public Runnable[] getAllTasks() {
		File dir = new File(localDir);
		File[]  files = dir.listFiles();
		
		
		return null;
	}

}
