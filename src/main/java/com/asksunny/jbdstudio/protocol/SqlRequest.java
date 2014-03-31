package com.asksunny.jbdstudio.protocol;

public class SqlRequest 
{
	String datasource;
	boolean printHeader;
	boolean printMeta;
	boolean printMetaOnly;
	String sql;
	int requestId;
	
	
	
	public int getRequestId() {
		return requestId==0 && sql!=null?sql.hashCode():requestId;
	}
	
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public boolean isPrintHeader() {
		return printHeader;
	}
	public void setPrintHeader(boolean printHeader) {
		this.printHeader = printHeader;
	}
	public boolean isPrintMeta() {
		return printMeta;
	}
	public void setPrintMeta(boolean printMeta) {
		this.printMeta = printMeta;
	}
	public boolean isPrintMetaOnly() {
		return printMetaOnly;
	}
	public void setPrintMetaOnly(boolean printMetaOnly) {
		this.printMetaOnly = printMetaOnly;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}
