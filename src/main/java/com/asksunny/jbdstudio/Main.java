package com.asksunny.jbdstudio;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asksunny.jbdstudio.sql.PreparedSQLStatement;
import com.asksunny.jbdstudio.sql.SQLNamedParameterParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sql = "SELECT * from test where id=#id:INT# and dob=#dob:DATE:[yyyy-MM-dd]#";
		PreparedSQLStatement p = SQLNamedParameterParser.parseSql(sql);
		System.out.println(p.getPreparedSql());
	}

}
