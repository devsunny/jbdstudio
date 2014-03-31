package com.asksunny.jbdstudio.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLNamedParameterParser 
{
	Pattern nameParamPattern = Pattern.compile("#([A-Za-z][A-Za-z0-9_]+)([:]([A-Za-z]+))?([:](\\[([^\\]]+)\\]))?#");
	
	
	public PreparedSQLStatement parse(String originalSql)
	{
		PreparedSQLStatement stmt = new PreparedSQLStatement(originalSql);		
		StringBuffer buf = new StringBuffer();
		Matcher m = nameParamPattern.matcher(originalSql);
		int pos = 0;
		List<SQLParameter> params = new ArrayList<SQLParameter>();
		while(m.find()){
			pos++;
			SQLParameter param = new SQLParameter(m.group(1), m.group(3), m.group(6));
			param.setParameterIndex(pos);
			params.add(param);
			m.appendReplacement(buf, "?");
		}
		m.appendTail(buf);
		stmt.setPreparedSql(buf.toString());
		stmt.setParams(params);
		return stmt;
	}
	
	public static PreparedSQLStatement parseSql(String originalSql)
	{
		SQLNamedParameterParser parser = new SQLNamedParameterParser();
		return parser.parse(originalSql);
	}
	
}
