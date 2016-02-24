package com.cafetrex.databaseConnectors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class mySqlConnectorTest {
	
	static mySqlConnector conn;
	
	@BeforeClass
	public static void setUp() {
		try {
			System.out.println("Initializing Connector...");
			conn = new mySqlConnector();
			System.out.println("Database connected!");
		} catch (SQLException e) {
			System.out.println("Connector initialization failed...");
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public static void tearDown() {
		try {
			System.out.println("\nClosing connectior...");
			conn.closeConnector();
			System.out.println("Connector successfully closed.");
		} catch (SQLException e) {
			System.out.println("Connector failed to be closed...");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRunQuery() {
		String query = "SELECT * FROM Menu ORDER BY 'menu_ID'";
		
		conn.toString();
		
		try {
			ResultSet rs = conn.runQuery(query);
			Assert.assertTrue(rs != null);
		} catch (SQLException e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}
	

}
