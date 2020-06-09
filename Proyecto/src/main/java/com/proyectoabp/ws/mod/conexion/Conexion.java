package com.proyectoabp.ws.mod.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
	
static Connection con= null;
	public static Connection getConenction() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://database-1.cdhit3elrflp.us-east-1.rds.amazonaws.com:3306/AbpDataBase","admin","f03R9CPHJMuNCyUuXBRQ");
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return con;
	}
	
	
	/*
	
private static BasicDataSource data=null;
	
	private static DataSource getDataSource() {
		
		if (data == null) {
			data = new BasicDataSource();
			data.setDriverClassName("com.mysql.cj.jdbc.Driver");
			data.setUsername("root");
			data.setPassword("");
			data.setUrl("jdbc:mysql://127.0.0.1:3306/abpdatabase?serverTimezone=UTC");
			data.setInitialSize(0);
			data.setMaxIdle(8);
			data.setMaxTotal(8);
			data.setMaxWaitMillis(5000);
		}
		return data;
	}
	public static Connection getConenction() throws SQLException {
		return getDataSource().getConnection();
	}
	
*/
}
