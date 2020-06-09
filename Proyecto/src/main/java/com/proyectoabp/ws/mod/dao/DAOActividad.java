package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOSesiones;

public class DAOActividad {
	
	public String getProblematica(VOActividad vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOActividad> grupos= new ArrayList<VOActividad>();
		PreparedStatement preparedStmt = null;
		String query = "select * from problematica";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getGrupoS());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("problema");
				est=rs.getString("num");
				//est= rs.getString("estudiante");
				//nombre = rs.getString("grupo");
				//profe = rs.getString("numeroGrupo");
				grupos.add(new VOActividad(code,est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	
	public boolean updatePro(VOActividad vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "update problematica set num=1 where problema=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getProblematica());
			//preparedStmt.setString(1, vo.getCodeito());
			
			result = preparedStmt.executeUpdate();
			String usera = null;
			String password = null;
			String tipo = null;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 0) {
			return false;
		} else {
			return true;
		}
	}
	public String getPro(VOActividad vo) throws SQLException {
		//boolean temp = false;
		String s=null;
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM problematica WHERE num=1";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getProbNum());
			//preparedStmt.setString(2, vo.getPassword());
			ResultSet rs = preparedStmt.executeQuery();
			String usera = null;
			String password = null;
			String tipo = null;
			if (rs.next()) {
				s=rs.getString("problema");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
