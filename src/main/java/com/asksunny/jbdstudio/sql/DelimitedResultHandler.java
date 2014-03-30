package com.asksunny.jbdstudio.sql;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class DelimitedResultHandler extends Writer implements
		SQLExecutionResultHandler {

	Writer out;
	boolean printHeader = false;
	boolean printMetaData = false;
	boolean printMetaDataOnly = false;
	char coldelimiter = '|';
	char recordDelimiter = '\n';
	char metaDelimiter = ':';

	public DelimitedResultHandler(Writer out) {
		super();
		this.out = out;
	}

	public DelimitedResultHandler(Writer out, boolean printHeader,
			boolean printMetaData) {
		super();
		this.out = out;
		this.printHeader = printHeader;
		this.printMetaData = printMetaData;
	}

	public DelimitedResultHandler(Writer out, boolean printHeader,
			boolean printMetaData, char coldelimiter, char recordDelimiter) {
		super();
		this.out = out;
		this.printHeader = printHeader;
		this.printMetaData = printMetaData;
		this.coldelimiter = coldelimiter;
		this.recordDelimiter = recordDelimiter;
	}
	
	public DelimitedResultHandler(Writer out, boolean printHeader,
			boolean printMetaData, String coldelimiter, String recordDelimiter) {
		super();
		this.out = out;
		this.printHeader = printHeader;
		this.printMetaData = printMetaData;
		setColdelimiter(coldelimiter);
		setRecordDelimiter(recordDelimiter);
	}
	

	public void onUpdateReturn(String originalSql, int affectedCounts) {
		try {
			this.out.write(Integer.toString(affectedCounts));
		} catch (IOException e) {
			;
		}
	}

	public void onQueryResultReturn(String originalSql, ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			int i = 0;
			if (this.printHeader || this.printMetaData) {				
				for (i = 1; i <= colCount; i++) {
					String colName = rsmd.getColumnName(i);
					if (this.printMetaData) {
						int jdbcType = rsmd.getColumnType(i);
						int displaySize = rsmd.getColumnDisplaySize(i);
						if (jdbcType == Types.VARCHAR
								|| jdbcType == Types.NVARCHAR
								|| jdbcType == Types.CHAR
								|| jdbcType == Types.NCHAR
								|| jdbcType == Types.CLOB
								|| jdbcType == Types.NCLOB
								|| jdbcType == Types.LONGVARCHAR
								|| jdbcType == Types.LONGNVARCHAR) {
							this.out.write(String.format("%s%c%s%c%d", colName,
									metaDelimiter,
									JDBCTypeMap.getJDBCTypeName(jdbcType),
									metaDelimiter, displaySize));
						} else if (jdbcType == Types.NUMERIC
								|| jdbcType == Types.DOUBLE
								|| jdbcType == Types.FLOAT
								|| jdbcType == Types.REAL
								|| jdbcType == Types.DECIMAL) {
							this.out.write(String.format("%s%c%s%c%d%c%d", colName,
									metaDelimiter,
									JDBCTypeMap.getJDBCTypeName(jdbcType),
									metaDelimiter, rsmd.getScale(i),
									metaDelimiter, rsmd.getPrecision(i)));
						} else {
							this.out.write(String.format("%s%c%s", colName,
									metaDelimiter,
									JDBCTypeMap.getJDBCTypeName(jdbcType)));
						}
					} else {
						this.out.write(colName);
					}
					if(i<colCount) this.out.write(getColdelimiter());					
				}
				 this.out.write(getRecordDelimiter());
			}
			if(printMetaDataOnly) return;
			while (rs.next()) {
				for (i = 1; i <= colCount; i++) {
					this.out.write(rs.getString(i));
					if(i<colCount) this.out.write(getColdelimiter());					
				}
				this.out.write(getRecordDelimiter());
			}
		} catch (Exception e) {
			onSqlExecutionError(originalSql, new SQLException("Failed to process resultset.", e));
		}

	}

	public void onSqlExecutionError(String originalSql, SQLException sex) {
		sex.printStackTrace(new PrintWriter(this.out));
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		this.out.write(cbuf, off, len);
		this.out.write(getRecordDelimiter());
	}

	@Override
	public void flush() throws IOException {
		this.out.flush();

	}

	@Override
	public void close() throws IOException {
		this.out.close();

	}

	public char getColdelimiter() {
		return coldelimiter;
	}
	
	public void setColdelimiter(String coldelimiter) {
		if(coldelimiter.length()==1){
			this.coldelimiter = coldelimiter.charAt(0);
		}else if(coldelimiter.matches("\\d{3}")){
			this.coldelimiter = (char)Integer.valueOf(coldelimiter).intValue();
		}else{
			throw new IllegalArgumentException("only single character delimiter is supported.");
		}
	}
	

	public void setColdelimiter(char coldelimiter) {
		this.coldelimiter = coldelimiter;
	}

	public char getRecordDelimiter() {
		return recordDelimiter;
	}

	public void setRecordDelimiter(char recordDelimiter) {
		this.recordDelimiter = recordDelimiter;
	}
	
	public void setRecordDelimiter(String recordDelimiter) {		
		if(recordDelimiter.length()==1){
			this.recordDelimiter = recordDelimiter.charAt(0);
		}else if(recordDelimiter.matches("\\d{3}")){
			this.coldelimiter = (char)Integer.valueOf(recordDelimiter).intValue();
		}else{
			throw new IllegalArgumentException("only single character delimiter is supported.");
		}
	}
	
	

	public boolean isPrintHeader() {
		return printHeader;
	}

	public void setPrintHeader(boolean printHeader) {
		this.printHeader = printHeader;
	}

	public boolean isPrintMetaData() {
		return printMetaData;
	}

	public void setPrintMetaData(boolean printMetaData) {
		this.printMetaData = printMetaData;
	}

	public char getMetaDelimiter() {
		return metaDelimiter;
	}

	public void setMetaDelimiter(char metaDelimiter) {
		this.metaDelimiter = metaDelimiter;
	}

	public boolean isPrintMetaDataOnly() {
		return printMetaDataOnly;
	}

	public void setPrintMetaDataOnly(boolean printMetaDataOnly) {
		this.printMetaDataOnly = printMetaDataOnly;
	}

}
