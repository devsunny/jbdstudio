package com.asksunny.jbdstudio;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;

import com.asksunny.jbdstudio.sql.PreparedSQLStatement;
import com.asksunny.jbdstudio.sql.SQLNamedParameterParser;

public class SystemMonitorService 
{
	
	
	public static void main(String[] args) throws Exception {
		//String p = System.getProperty("java.library.path");		
		//p = "C:\\Users\\Sunny Liu\\Downloads\\hyperic-sigar-1.6.4.zip\\hyperic-sigar-1.6.4\\sigar-bin\\lib;"+ p;
		//System.setProperty("java.library.path", p);		
		
		
		Sigar sigar = new Sigar();
		CpuInfo[]  infos =    sigar.getCpuInfoList();
		CpuInfo cpuinfo = infos[0];		
		
		System.out.println(String.format("core per socket:%d", cpuinfo.getCoresPerSocket()));
		System.out.println(String.format("total socket:%d", cpuinfo.getTotalSockets()));
		System.out.println(String.format("total core:%d", cpuinfo.getTotalCores()));
		Cpu[] cpus = sigar.getCpuList();
		for (int i = 0; i < cpus.length; i++) {
			Cpu cpu = cpus[i];
			
		}
		
		
		
	}

}
