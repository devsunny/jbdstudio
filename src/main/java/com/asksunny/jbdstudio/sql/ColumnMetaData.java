package com.asksunny.jbdstudio.sql;

public class ColumnMetaData {
	String name;
	String jdbcTypeName;
	int jdbcType;
	int displaySize;
	int scale = -1;
	int precision = -1;
	String tablename;
	String schemaname;
	String catalogName;

	public ColumnMetaData(String name, int jdbcType, int displaySize,
			int scale, int precision) {
		super();
		this.name = name;
		this.jdbcType = jdbcType;
		this.jdbcTypeName = JDBCTypeMap.getJDBCTypeName(jdbcType);
		this.displaySize = displaySize;
		this.scale = scale;
		this.precision = precision;
	}
	
	

	public ColumnMetaData(String name, int jdbcType, int displaySize,
			int scale, int precision, String tablename, String schemaname,
			String catalogName) {
		super();
		this.name = name;
		this.jdbcType = jdbcType;
		this.jdbcTypeName = JDBCTypeMap.getJDBCTypeName(jdbcType);
		this.displaySize = displaySize;
		this.scale = scale;
		this.precision = precision;
		this.tablename = tablename;
		this.schemaname = schemaname;
		this.catalogName = catalogName;
	}



	public ColumnMetaData() {
		super();
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
		this.jdbcTypeName = JDBCTypeMap.getJDBCTypeName(jdbcType);
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

}
