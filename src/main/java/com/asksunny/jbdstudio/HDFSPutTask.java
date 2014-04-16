package com.asksunny.jbdstudio;


import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSPutTask implements Runnable {

	
	int retStatus = -1;
	int maxRetry = 3;
	String localPath;
	String removePath;
	
	
	public int getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(int retStatus) {
		this.retStatus = retStatus;
	}

	public HDFSPutTask(String localPath, String removePath, int maxretry) {
		super();
		this.localPath = localPath;
		this.removePath = removePath;
		this.maxRetry = maxretry;
	}
	
	public HDFSPutTask(String localPath, String removePath) {
		super();
		this.localPath = localPath;
		this.removePath = removePath;
	}

	public void run() {
		int tried = 0;
		boolean doTry = true;
		while(doTry){			
			try {
				LocalFileSystem fs = new LocalFileSystem();				
				try{
					Path local  = new Path(localPath);
					Path remove  = new Path(removePath);
					fs.copyFromLocalFile(local, remove);
					retStatus = 0;
					doTry = false;
				}finally{
					fs.close();
				}
			} catch (Exception e) {			
				tried++;
				e.printStackTrace();
				retStatus = -1;
				if(tried>=maxRetry && retStatus==-1) doTry = false;
			}
		}
	}

}
