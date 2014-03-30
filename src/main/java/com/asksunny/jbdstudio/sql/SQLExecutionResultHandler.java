package com.asksunny.jbdstudio.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLExecutionResultHandler 
{
	public void onUpdateReturn(String originalSql, int affectedCounts);
	public void onQueryResultReturn(String originalSql, ResultSet rs);
	public void onSqlExecutionError(String originalSql, SQLException sex);
}
