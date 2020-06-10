package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOHipotesis;
import com.proyectoabp.ws.rest.vo.VOIdeas;
import com.proyectoabp.ws.rest.vo.VOPalabras;
import com.proyectoabp.ws.rest.vo.VOProblema;
import com.proyectoabp.ws.rest.vo.VORespuesta;

public class DAOActivity {
// PROBLEMA ////////////////////////
	
	public boolean SubirProblema(VOProblema vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into problema(problem, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getProblem());
			preparedStmt.setString(2, vo.getNum1());
			
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

	// IDEAS ///////////////////////
	public boolean SubirIdea(VOIdeas vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into ideas(idea, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getIdea());
			preparedStmt.setString(2, vo.getNum2());
			
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
	
	// HIPOTESIS ////////////////////////////
	public boolean SubirHipo(VOHipotesis vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into hipotesis(hipo, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getHipo());
			preparedStmt.setString(2, vo.getNum3());
			
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
	
	// PALABRAS CLAVE
	
	public boolean SubirPalabras(VOPalabras vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into palabras_clave(palabras, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getPalabras());
			preparedStmt.setString(2, vo.getNum4());
			
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
	
	//IDEAS 2
	
	public boolean SubirIdea2(VOIdeas vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into ideas2(idea, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getIdea());
			preparedStmt.setString(2, vo.getNum2());
			
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
	//RESPUESTA
	
	public boolean Respuesta(VORespuesta vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into respuesta(hipo, NumG) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getRespuesta());
			preparedStmt.setString(2, vo.getNum6());
			
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
}
