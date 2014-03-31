package com.asksunny.jbdstudio.sql;

import java.util.List;

public class PreparedSQLStatement 
{
	String originalSql;
	String preparedSql;
	List<SQLParameter> params;
	
	
	public boolean hasParameter()
	{
		return (params!=null && params.size()>0);
	}
	
	public PreparedSQLStatement(String originalSql) {
		super();
		this.originalSql = originalSql;
	}
	public PreparedSQLStatement() {
		super();		
	}
	public String getOriginalSql() {
		return originalSql;
	}
	public void setOriginalSql(String originalSql) {
		this.originalSql = originalSql;
	}
	public String getPreparedSql() {
		return preparedSql;
	}
	public void setPreparedSql(String preparedSql) {
		this.preparedSql = preparedSql;
	}
	public List<SQLParameter> getParams() {
		return params;
	}
	public void setParams(List<SQLParameter> params) {
		this.params = params;
	}
	
	
	
}
