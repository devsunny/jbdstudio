package com.asksunny.jbdstudio.cfg;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JBDStudioConfigTest {

	JBDStudioConfig config = null;
	@Before
	public void setUp() throws Exception {		
		config = new JBDStudioConfig("classpath:jbdstudio.properties");
	}
	

	@After
	public void tearDown() throws Exception {
		config = null;
	}

	@Test
	public void testGetAllDataSourceConfig()  throws SQLException{		
		JDBCDataSourceManager configs = config.getJDBCDataSourceManager();	
		assertEquals(1, configs.size());
		JBDDataSource ds = (JBDDataSource) configs.getDataSource();
		assertEquals("default", ds.getName());		
		Connection conn = ds.getConnection();
		assertNotNull(conn);
		conn.close();
		configs.shutdown();
	}

}
