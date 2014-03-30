package com.asksunny.jbdstudio.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.asksunny.jbdstudio.transform.PipelineTransformer;

public class PipelineResultHandler implements SQLExecutionResultHandler {

	public void onUpdateReturn(String originalSql, int affectedCounts) {

	}

	public void onQueryResultReturn(String originalSql, ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			int i = 0;
			ColumnMetaData[] metas = new ColumnMetaData[colCount];
			for (i = 1; i <= colCount; i++) {
				metas[i] = new ColumnMetaData(rsmd.getColumnName(i),
						rsmd.getColumnType(i), rsmd.getColumnDisplaySize(i),
						rsmd.getScale(i), rsmd.getPrecision(i),
						rsmd.getTableName(i), rsmd.getSchemaName(i),
						rsmd.getCatalogName(i));
			}
			final Object[] values = new Object[colCount];
			PipelineTransformer transformer = new PipelineTransformer(metas);
			while (rs.next()) {
				for (i = 1; i <= colCount; i++) {
					values[i] = rs.getObject(i);
				}
				if(!transformer.processDataRow(values)) break;
			}
		} catch (Exception e) {
			onSqlExecutionError(originalSql, new SQLException(
					"Failed to process resultset.", e));
		}

	}

	public void onSqlExecutionError(String originalSql, SQLException sex) 
	{
		sex.printStackTrace();
	}

}
