package com.asksunny.jbdstudio.sql;

public class SQLParameter {

	String name;
	String jdbcTypeName;
	int jdbcType;
	String format;
	int parameterIndex;
	
	
	
	public SQLParameter() {
		super();		
	}


	public SQLParameter(String name, String jdbcTypeName, String format) {
		super();
		this.name = name;
		this.jdbcTypeName = jdbcTypeName;
		this.jdbcType = JDBCTypeMap.getJDBCType(jdbcTypeName);
		this.format = format;
	}
	
	
	public int getParameterIndex() {
		return parameterIndex;
	}


	public void setParameterIndex(int parameterIndex) {
		this.parameterIndex = parameterIndex;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJdbcTypeName() {
		return jdbcTypeName;
	}
	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
	}
	public int getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
	
}
