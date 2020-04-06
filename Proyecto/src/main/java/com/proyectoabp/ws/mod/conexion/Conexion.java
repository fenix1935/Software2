package com.proyectoabp.ws.mod.conexion;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

	private static BasicDataSource data=null;
	
	private static DataSource getDataSource() {
		
		if (data == null) {
			data = new BasicDataSource();
			data.setDriverClassName("com.mysql.cj.jdbc.Driver");
			data.setUsername("root");
			data.setPassword("");
			data.setUrl("jdbc:mysql://localhost:3306/abpdatabase?serverTimezone=UTC");
			data.setInitialSize(20);
			data.setMaxIdle(15);
			data.setMaxTotal(15);
			data.setMaxWaitMillis(5000);
		}
		return data;
	}
	public static Connection getConenction() throws SQLException {
		return getDataSource().getConnection();
	}
}
