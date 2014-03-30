package com.asksunny.jbdstudio.sql;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asksunny.jbdstudio.cfg.JBDStudioConfig;

public class SQLExecutorTest {

	SQLExecutor executor = null;
	JBDStudioConfig config = null;
	TestSQLExecutionResultHandler handler = null;
	@Before
	public void setUp() throws Exception {		
		config = new JBDStudioConfig("classpath:jbdstudio.properties");		
		handler = new TestSQLExecutionResultHandler();
		executor = new SQLExecutor(config.getJDBCDataSourceManager().getDataSource(), handler, null);
	}

	@After
	public void tearDown() throws Exception {
		config.getJDBCDataSourceManager().shutdown();
	}

	@Test
	public void testExecuteSqlListOfStringBoolean() {
		String sql1 = "create table test (id int not null primary key, name varchar(128) not null)";		
		String sql2 = "insert into test (id , name ) values (1, 'Sunny Liu')";
		String sql3 = "insert into test (id , name ) values ('test', 'Sunny Liu')";
		String sql4 = "drop table test";
		executor.executeSql(sql1);
		List<String> sqls = new ArrayList<String>();		
		sqls.add(sql2);
		sqls.add(sql3);		
		executor.executeSql(sqls, true);
		executor.executeSql("select count(*) from test");
		executor.executeSql(sql4);
	}
	
	@Test
	public void testExecuteSqlListOfStringBooleanFalse() {
		String sql1 = "create table test (id int not null primary key, name varchar(128) not null)";		
		String sql2 = "insert into test (id , name ) values (1, 'Sunny Liu')";
		String sql3 = "insert into test (id , name ) values ('test', 'Sunny Liu')";
		String sql5 = "select * from test";
		String sql4 = "drop table test";
		executor.executeSql(sql1);
		List<String> sqls = new ArrayList<String>();		
		sqls.add(sql2);
		sqls.add(sql3);			
		executor.executeSql(sqls, false);
		StringWriter sw = new StringWriter();
		DelimitedResultHandler csvHandler = new DelimitedResultHandler(sw, true, true);
		executor.setResulthandler(csvHandler);
		executor.executeSql(sql5);
		System.out.println(sw.toString());
		executor.executeSql("select count(*) from test");
		executor.executeSql(sql4);
	}
	

	//@Test
	public void testExecuteSqlString() {
		String sql1 = "create table test (id int not null primary key, name varchar(128) not null)";		
		String sql2 = "insert into test (id , name ) values (1, 'Sunny Liu')";
		String sql3 = "drop table test";	
		executor.executeSql(sql1);
		//System.out.println(handler.getAffectedCount());
		executor.executeSql(sql2);
		//System.out.println(handler.getAffectedCount());
		executor.executeSql(sql3);
		//System.out.println(handler.getAffectedCount());
		
	}

}
