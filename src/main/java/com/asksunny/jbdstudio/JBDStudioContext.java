package com.asksunny.jbdstudio;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import com.asksunny.jbdstudio.util.CLIOptions;

public class JBDStudioContext extends HashMap<String, Object>
{

	public final static String SUFFIX = ".class";
	public final static String JAR_SUFFIX = ".jar";
	public final static String PLUGIN_DIR = "jbdstudio.plugin.dir";
	
	URLClassLoader pluginClassloader = null;
	JBDStudioConfiguration configuration;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JBDStudioContext(CLIOptions options)
	{
		configuration = new JBDStudioConfiguration(options);
		initPlugin();
	}
	

	public JBDStudioConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(JBDStudioConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	protected void initPlugin()
	{
		String pluginDir  = this.configuration.getOption(PLUGIN_DIR);
		if(pluginDir!=null){			
			File dir = new File(pluginDir);
			File[]  fs = dir.listFiles(new FilenameFilter() {				
				public boolean accept(File dir, String name) {					
					return name.endsWith(JAR_SUFFIX);
				}
			});
			URL[] jars = new URL[fs.length];
			try {
				for (int i = 0; i < jars.length; i++) {
					jars[i] = fs[i].toURI().toURL();
				}
				pluginClassloader = new URLClassLoader(jars, getClass().getClassLoader());
			} catch (MalformedURLException e) {				
				e.printStackTrace();
			}			
		}
		
	}
	
	
	
	
	public JBDStudioService loadService(String name)
	{
		String key = name + SUFFIX;
		String className = this.configuration.getOption(key);
		try {			
			Class<?> clazz = this.pluginClassloader!=null?Class.forName(className, true, this.pluginClassloader)
					:Class.forName(className);
			JBDStudioService service = (JBDStudioService) clazz.newInstance();		
			return service;
		}  catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load JBDStudioService %s:class name: %s", name, className));
		}
	}
	
	

}
