package com.asksunny.jbdstudio.cfg;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

public class JDBCDataSourceManager extends
		ConcurrentHashMap<String, JBDDataSource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return default datasource;
	 */
	public DataSource getDataSource() {
		return this.get("default");
	}

	/**
	 * 
	 * @return default datasource;
	 */
	public DataSource getDataSource(String dsname) {
		return this.get(dsname);
	}

	public void shutdown() {
		Collection<JBDDataSource> dss = this.values();
		for (Iterator<JBDDataSource> iterator = dss.iterator(); iterator.hasNext();) {
			JBDDataSource jbdDataSource =  iterator.next();
			try{
				jbdDataSource.close();				
			}catch(Exception ex){
				; //ignore it;
			}
		}
	}

}
