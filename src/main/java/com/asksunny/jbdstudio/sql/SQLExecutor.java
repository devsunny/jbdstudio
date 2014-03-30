package com.asksunny.jbdstudio.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

public class SQLExecutor {

	SQLExecutionResultHandler resulthandler;
	SQLParameterProvider parameterProvider;
	DataSource dataSource;

	public SQLExecutor(DataSource dataSource,
			SQLExecutionResultHandler resulthandler,
			SQLParameterProvider parameterProvider) {
		super();
		this.dataSource = dataSource;
		this.resulthandler = resulthandler;
		this.parameterProvider = parameterProvider;
	}
	
	
	@SuppressWarnings("resource")
	public void executeSql(List<String> sqls, boolean withTransaction) {
		Connection conn = null;
		Statement stmt = null;
		boolean autoCommit = true;
		String currSql = null;
		try {
			conn = dataSource.getConnection();
			autoCommit = conn.getAutoCommit();
			if(withTransaction) conn.setAutoCommit(false);
			stmt = conn.createStatement();			
			for(String sql: sqls){
				currSql = sql;
				try{
					boolean hasResult = stmt.execute(sql);
					int updateCount = -1;
					ResultSet rs = null;
					if (hasResult) {
						rs = stmt.getResultSet();
						if (resulthandler != null) {
							this.resulthandler.onQueryResultReturn(sql, rs);
						}
					} else {
						updateCount = stmt.getUpdateCount();
						if (updateCount != -1) {
							this.resulthandler.onUpdateReturn(sql,
									stmt.getUpdateCount());
						}
					}

					do {
						hasResult = stmt.getMoreResults();
						if (hasResult) {
							rs = stmt.getResultSet();
							if (resulthandler != null) {
								this.resulthandler.onQueryResultReturn(sql, rs);
							}
						} else {
							updateCount = stmt.getUpdateCount();
							if (updateCount != -1) {
								if (updateCount != -1) {
									this.resulthandler.onUpdateReturn(sql,
											stmt.getUpdateCount());
								}
							}
						}
					} while (hasResult || updateCount != -1);
					
					if(rs!=null && !rs.isClosed()){
						rs.close();
					}
					
				}catch(SQLException ex){
					if(!withTransaction){
						if (resulthandler != null) {
							this.resulthandler.onSqlExecutionError(sql, ex);
						}
					}else{
						throw ex;
					}
				}				
			}
			if(withTransaction)  conn.commit();
		} catch (SQLException ex) {
			if (resulthandler != null) {
				this.resulthandler.onSqlExecutionError(currSql, ex);
			}
			if(withTransaction)
				try {
					conn.rollback();
				} catch (SQLException e) {					
					throw new RuntimeException("Failed to rollback transaction", ex);
				}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				;
			} finally {
				try {
					if (conn != null){
						conn.setAutoCommit(autoCommit);
						conn.close();
					}
				} catch (SQLException e) {
					;
				}
			}

		}

	}
	

	public void executeSql(String sql) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			boolean hasResult = stmt.execute(sql);
			int updateCount = -1;
			ResultSet rs = null;
			if (hasResult) {
				rs = stmt.getResultSet();
				if (resulthandler != null) {
					this.resulthandler.onQueryResultReturn(sql, rs);
				}
			} else {
				updateCount = stmt.getUpdateCount();
				if (updateCount != -1) {
					this.resulthandler.onUpdateReturn(sql,
							stmt.getUpdateCount());
				}
			}

			do {
				hasResult = stmt.getMoreResults();
				if (hasResult) {
					rs = stmt.getResultSet();
					if (resulthandler != null) {
						this.resulthandler.onQueryResultReturn(sql, rs);
					}
				} else {
					updateCount = stmt.getUpdateCount();
					if (updateCount != -1) {
						if (updateCount != -1) {
							this.resulthandler.onUpdateReturn(sql,
									stmt.getUpdateCount());
						}
					}
				}
			} while (hasResult || updateCount != -1);
			
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
			
		} catch (SQLException ex) {
			if (resulthandler != null) {
				this.resulthandler.onSqlExecutionError(sql, ex);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				;
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					;
				}
			}

		}

	}

	public SQLExecutor() {

	}


	public SQLExecutionResultHandler getResulthandler() {
		return resulthandler;
	}


	public void setResulthandler(SQLExecutionResultHandler resulthandler) {
		this.resulthandler = resulthandler;
	}


	public SQLParameterProvider getParameterProvider() {
		return parameterProvider;
	}


	public void setParameterProvider(SQLParameterProvider parameterProvider) {
		this.parameterProvider = parameterProvider;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
