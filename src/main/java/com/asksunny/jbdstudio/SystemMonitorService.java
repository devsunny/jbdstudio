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
		for (int i = 0; i < infos.length; i++) {
			System.out.println(infos[i].getTotalSockets());
			System.out.println(infos[i].getTotalCores());			
		}
		Cpu[]  cpus = sigar.getCpuList();
		for (int i = 0; i < cpus.length; i++) {
			System.out.println(cpus[i].getTotal());
		}
		
	}

}
