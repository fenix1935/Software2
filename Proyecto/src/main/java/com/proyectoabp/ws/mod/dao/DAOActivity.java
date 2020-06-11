package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOCalificacion;
import com.proyectoabp.ws.rest.vo.VOHipotesis;
import com.proyectoabp.ws.rest.vo.VOIdeas;
import com.proyectoabp.ws.rest.vo.VOPalabras;
import com.proyectoabp.ws.rest.vo.VOProblema;
import com.proyectoabp.ws.rest.vo.VORespuesta;
import com.proyectoabp.ws.rest.vo.VOSesiones;

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
	
	public String GetProblema(VOProblema vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOProblema> grupos= new ArrayList<VOProblema>();
		PreparedStatement preparedStmt = null;
		String query = "select * from problema where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getProblem());
			preparedStmt.setString(1, vo.getNum1());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("problem");
				est= rs.getString("NumG");
				grupos.add(new VOProblema(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	public String GetProblemaAll(VOProblema vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOProblema> grupos= new ArrayList<VOProblema>();
		PreparedStatement preparedStmt = null;
		String query = "select * from problema";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getProblem());
			//preparedStmt.setString(1, vo.getNum1());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("problem");
				est= rs.getString("NumG");
				grupos.add(new VOProblema(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	public String GetHipotesisAll(VOProblema vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOProblema> grupos= new ArrayList<VOProblema>();
		PreparedStatement preparedStmt = null;
		String query = "select * from hipotesis";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getProblem());
			//preparedStmt.setString(1, vo.getNum1());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("hipo");
				est= rs.getString("NumG");
				grupos.add(new VOProblema(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	public String GetRespuestaAll(VOProblema vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOProblema> grupos= new ArrayList<VOProblema>();
		PreparedStatement preparedStmt = null;
		String query = "select * from respuesta";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getProblem());
			//preparedStmt.setString(1, vo.getNum1());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("hipo");
				est= rs.getString("NumG");
				grupos.add(new VOProblema(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
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
	
	public String GetIdea(VOIdeas vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOIdeas> grupos= new ArrayList<VOIdeas>();
		PreparedStatement preparedStmt = null;
		String query = "select * from ideas where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getIdea());
			preparedStmt.setString(1, vo.getNum2());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("idea");
				est= rs.getString("NumG");
				grupos.add(new VOIdeas(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
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
	
	public String GetHipotesis(VOHipotesis vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOHipotesis> grupos= new ArrayList<VOHipotesis>();
		PreparedStatement preparedStmt = null;
		String query = "select * from hipotesis where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getHipo());
			preparedStmt.setString(1, vo.getNum3());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("hipo");
				est= rs.getString("NumG");
				grupos.add(new VOHipotesis(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
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
	
	public String GetPalabras(VOPalabras vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOPalabras> grupos= new ArrayList<VOPalabras>();
		PreparedStatement preparedStmt = null;
		String query = "select * from palabras_clave where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getPalabras());
			preparedStmt.setString(1, vo.getNum4());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("palabras");
				est= rs.getString("NumG");
				grupos.add(new VOPalabras(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
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
	public String GetIdea2(VOIdeas vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOIdeas> grupos= new ArrayList<VOIdeas>();
		PreparedStatement preparedStmt = null;
		String query = "select * from ideas2 where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getIdea());
			preparedStmt.setString(1, vo.getNum2());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("idea");
				est= rs.getString("NumG");
				grupos.add(new VOIdeas(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
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
	public String GetRespuesta(VORespuesta vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VORespuesta> grupos= new ArrayList<VORespuesta>();
		PreparedStatement preparedStmt = null;
		String query = "select * from respuesta where NumG=?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getRespuesta());
			preparedStmt.setString(1, vo.getNum6());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("hipo");
				est= rs.getString("NumG");
				grupos.add(new VORespuesta(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	// CALIFICACION
	public boolean cali(VOCalificacion vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into calificaciones(calificacion, nombre) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getCali());
			preparedStmt.setString(2, vo.getNamS());
			
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
	public String GetCali(VOCalificacion vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOIdeas> grupos= new ArrayList<VOIdeas>();
		PreparedStatement preparedStmt = null;
		String query = "select * from calificaciones";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getCali());
			//preparedStmt.setString(2, vo.getNamS());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("calificacion");
				est= rs.getString("nombre");
				grupos.add(new VOIdeas(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	//CALIFICACION FINAL
	public boolean cali2(VOCalificacion vo)  throws SQLException{
		int result = 0;
		PreparedStatement preparedStmt = null;
		String query = "insert into calificacion_final(calificacion, nombre) values(?,?);";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, vo.getCali());
			preparedStmt.setString(2, vo.getNamS());
			
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

	public String GetCali2(VOCalificacion vo) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOIdeas> grupos= new ArrayList<VOIdeas>();
		PreparedStatement preparedStmt = null;
		String query = "select * from calificacion_final";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, vo.getCali());
			//preparedStmt.setString(2, vo.getNamS());
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String est= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("calificacion");
				est= rs.getString("nombre");
				grupos.add(new VOIdeas(code, est));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}

}
