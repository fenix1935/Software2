package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOGrupos;
import com.proyectoabp.ws.rest.vo.VOSesiones;
import com.proyectoabp.ws.rest.vo.VOUsuario;

public class DAOSesiones {
	public boolean isSesion(VOSesiones vo) throws SQLException {
		boolean temp = false;
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM sesiones WHERE grupo=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getGrupoS());
			//preparedStmt.setString(2, vo.getPassword());
			ResultSet rs = preparedStmt.executeQuery();
			String usera = null;
			String password = null;
			String tipo = null;
			if (rs.next()) {
				temp = true;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public boolean addSesion(VOSesiones vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into sesiones (estudiante, grupo, numeroGrupo) values (?, ?,?)";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getEstudianteS());
			preparedStmt.setString(2, vo.getGrupoS());
			preparedStmt.setString(3, "1");
			
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
	public String getSesiones(VOSesiones vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOSesiones> grupos= new ArrayList<VOSesiones>();
		PreparedStatement preparedStmt = null;
		String query = "select * from sesiones where grupo=? and numeroGrupo>0 and numeroGrupo<5";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getGrupoS());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("codiguito");
				est= rs.getString("estudiante");
				nombre = rs.getString("grupo");
				profe = rs.getString("numeroGrupo");
				grupos.add(new VOSesiones(est, nombre, profe));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	public boolean deleteSesion(VOSesiones vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "delete from sesiones where estudiante=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getEstudianteS());
			
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
	public boolean addSesionInicial(VOSesiones vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		PreparedStatement preparedS1 = null;
		String query1="truncate table sesiones";
		
		String query = "insert into sesiones (estudiante, grupo, numeroGrupo) values (?, ?,?)";
		try {
			Connection connection = Conexion.getConenction();
			preparedS1=connection.prepareStatement(query1);
			preparedS1.executeUpdate();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getEstudianteS());
			preparedStmt.setString(2, vo.getGrupoS());
			preparedStmt.setString(3, "0");
			
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
	public boolean pasarSesion(VOSesiones vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "update sesiones set numeroGrupo=? where codiguito='1'";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getGnum());
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
	public String isSesion1(VOSesiones vo) throws SQLException {
		//boolean temp = false;
		String s=null;
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM sesiones WHERE codiguito=1";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getGrupoS());
			//preparedStmt.setString(2, vo.getPassword());
			ResultSet rs = preparedStmt.executeQuery();
			String usera = null;
			String password = null;
			String tipo = null;
			if (rs.next()) {
				s=rs.getString("numeroGrupo");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public Boolean isSesion2(VOSesiones vo) throws SQLException {
		//boolean temp = false;
		boolean s=false;
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM sesiones WHERE estudiante=? and grupo=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getEstudianteS());
			preparedStmt.setString(2, vo.getGrupoS());
			ResultSet rs = preparedStmt.executeQuery();
			String usera = null;
			String password = null;
			String tipo = null;
			if (rs.next()) {
				//s=rs.getString("numeroGrupo");
				s=true;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}
