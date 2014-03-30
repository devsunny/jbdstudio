package com.asksunny.jbdstudio.cfg;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;

public class JBDStudioConfig extends HashMap<String, String> {

	public final static String DATASOURCE_PROP_PREFIX = "jdbc.datasource.";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String copyRight = "";
	String owner = "";
	String author = "";
	ReentrantLock lock = new ReentrantLock();
	JDBCDataSourceManager configsMap = null;
	
	public JBDStudioConfig(String cfgFile) {
		try {
			Properties props = new Properties();
			if (cfgFile.toLowerCase().startsWith("classpath:")) {
				InputStream in = this.getClass().getResourceAsStream(
						"/" + cfgFile.substring(10));
				try {
					props.load(in);
				} finally {
					in.close();
				}
				mergeProperties(props);
			} else {

				FileReader reader = new FileReader(cfgFile);
				try {
					props.load(reader);
				} finally {
					if (reader != null)
						reader.close();
				}
				mergeProperties(props);
			}
		} catch (Throwable t) {
			throw new RuntimeException("Failed to load properties file:"
					+ cfgFile, t);
		}
		mergeProperties(System.getProperties());

	}

	private void mergeProperties(Properties props) {
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement().toString();
			this.put(key, props.getProperty(key));
		}
	}

	public JDBCDataSourceManager getJDBCDataSourceManager() {
		
		Set<String> keys = this.keySet();
		try{
			lock.lock();
			if(configsMap==null){
				 configsMap = new JDBCDataSourceManager();		
				for (String key : keys) {
					if (key.toLowerCase().startsWith(DATASOURCE_PROP_PREFIX)) {
						int idx = key.indexOf(".", DATASOURCE_PROP_PREFIX.length());
						String ds_name = key.substring(DATASOURCE_PROP_PREFIX.length(), idx);				
						JBDDataSource dsCfg = configsMap.get(ds_name);
						if(dsCfg==null){
							dsCfg = new JBDDataSource();
							 dsCfg.setName(ds_name);
							 configsMap.put(ds_name, dsCfg);
						}				
						String dsPrefix = String.format("%s%s.", DATASOURCE_PROP_PREFIX, ds_name).toLowerCase();
						String value = this.get(key);
						String name = key.substring(dsPrefix.length());
						try{
							BeanUtils.setProperty(dsCfg, name, value);	
						}catch(Exception e){
							e.printStackTrace();
							//ignore it;
						}
					}
				}
			}
		}finally{
			lock.unlock();
		}
		return this.configsMap;
	}

}
