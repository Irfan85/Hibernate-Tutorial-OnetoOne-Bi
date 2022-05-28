package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:mariadb://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String userName = "root";
		String password = "";
		
		try {
			System.out.println("Connecting to database: " + jdbcURL);
			
			Connection connection = DriverManager.getConnection(jdbcURL, userName, password);
			
			System.out.println("Connection successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
