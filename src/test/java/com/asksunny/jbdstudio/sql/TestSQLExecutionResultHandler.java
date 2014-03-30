package com.asksunny.jbdstudio.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSQLExecutionResultHandler implements SQLExecutionResultHandler {

	int affectedCount = 0;

	public void onUpdateReturn(String originalSql, int affectedCounts) {
		this.affectedCount = affectedCounts;
		
	}

	public void onQueryResultReturn(String originalSql, ResultSet rs) {
		try {
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onSqlExecutionError(String originalSql, SQLException sex) {
		// TODO Auto-generated method stub
		
	}

	public int getAffectedCount() {
		return affectedCount;
	}

	public void setAffectedCount(int affectedCount) {
		this.affectedCount = affectedCount;
	}
	
	

}
