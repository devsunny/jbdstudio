package com.asksunny.jbdstudio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JBDStudioParallelRunner {
	int poolSize;

	ExecutorService executor = null;

	public JBDStudioParallelRunner(int size) {
		executor = Executors.newFixedThreadPool(size);
		this.poolSize = size;
	}
	
	
	

}
