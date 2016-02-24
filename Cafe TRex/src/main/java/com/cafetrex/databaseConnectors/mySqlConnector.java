package com.cafetrex.databaseConnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySqlConnector {

	private Connection connection = null;

	// Constructor for initialization
	public mySqlConnector() throws SQLException {
		String url = "jdbc:mysql://cafetrex.com:3306/cafe";
		String username = "root";
		String password = "csci4830";

		connection = DriverManager.getConnection(url, username, password);
	}

	public ResultSet runQuery(String query) throws SQLException {
		System.out.println("Executing query: '" + query + "'");
		Statement stmt = connection.createStatement();
		return stmt.executeQuery(query);
	}
	
	public int runUpdate(String query) throws SQLException {
		System.out.println("Executing update: '" + query + "'");
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		
		//Return new primary key
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			int newId = rs.getInt(1);
			return newId;
		}
		
		return -1;
	}

	public void addData(String query) throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.execute();
	}
	
	public void closeConnector() throws SQLException {
		if( !connection.isClosed() ) {
			connection.close();
		}
	}
}
