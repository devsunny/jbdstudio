package com.asksunny.jbdstudio.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Types;
import java.util.concurrent.ConcurrentHashMap;

public class JDBCTypeMap {
	private static JDBCTypeMap instance = new JDBCTypeMap();	
	private final ConcurrentHashMap<Integer, String> typeMap = new ConcurrentHashMap<Integer, String>();
	private final ConcurrentHashMap<String, Integer> rtypeMap = new ConcurrentHashMap<String, Integer>();
	private JDBCTypeMap(){ init(); };		
				
	
	
	private void init()
	{
		Field[]  fields = Types.class.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if(Modifier.isStatic(field.getModifiers()) && field.getType()==int.class){
					int type = field.getInt(null);
					String name = field.getName();
					typeMap.put(type, name);
					rtypeMap.put(name, type);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to create JDBC type Map.",e);
		} 
	}
	
	public static int getJDBCType(String jdbcTypeName)
	{
		if(instance.rtypeMap.containsKey(jdbcTypeName)){
			return instance.rtypeMap.get(jdbcTypeName);
		}else{
			return Types.OTHER;
		}
	}
	
	
	public static String getJDBCTypeName(int jdbcType)
	{
		if(instance.typeMap.containsKey(jdbcType)){
			return instance.typeMap.get(jdbcType);
		}else{
			return instance.typeMap.get(Types.OTHER);
		}
	}
	
}
