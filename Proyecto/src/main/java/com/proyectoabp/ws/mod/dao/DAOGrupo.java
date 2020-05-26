
package com.proyectoabp.ws.mod.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.gson.Gson;
import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOGrupos;
import com.proyectoabp.ws.rest.vo.VOGruposEst;
import com.proyectoabp.ws.rest.vo.VOUsuario;


public class DAOGrupo {
	public boolean addGroup(VOGrupos g) throws SQLException {
		int result = 0;
		Connection connection = Conexion.getConenction();
		String query = "insert into grupos (AccessCode, nombre, profesor)" + " values (?,?,?)";
		PreparedStatement preparedStmt = null;
		try {
			String vacio= " ";
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, g.getCodigo());
			preparedStmt.setString(1, g.getCodigoAcceso());
			preparedStmt.setString(2, g.getNombreCurso());
			preparedStmt.setString(3, g.getProfesor());

			result = preparedStmt.executeUpdate();
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
	public boolean addGroupEstudiante(String codeGrupo, String estudent) throws SQLException {
		int result = 0;
		Connection connection = Conexion.getConenction();
		String query = "insert into estudiantes_grupos(codigoGrupo,estudiante,nota,codi)" + " values (?,?,?, concat(codigoGrupo,estudiante))";
		PreparedStatement preparedStmt = null;
		try {
			String vacio= " ";
			preparedStmt = connection.prepareStatement(query);
			//preparedStmt.setString(1, g.getCodigo());
			preparedStmt.setString(1, codeGrupo);
			preparedStmt.setString(2, estudent);
			preparedStmt.setString(3, "0");

			result = preparedStmt.executeUpdate();
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
	public VOGrupos getGroup(String acceso) {
		String h=null;
		Gson gson= new Gson();
		VOGrupos a= new VOGrupos();
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM grupos where AccessCode = ?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, acceso);
			ResultSet rs = preparedStmt.executeQuery();
			String code= null;
			String acces= null;
			String nombre = null;
			String profe = null;
			if (rs.next()) {
				code= rs.getString("codigo");
				a.setCodigo(code);
				acces= rs.getString("AccessCode");
				a.setCodigoAcceso(acces);
				nombre = rs.getString("nombre");
				a.setNombreCurso(nombre);
				profe = rs.getString("profesor");
				a.setProfesor(profe);
				//grupos.add(new VOGrupos(acces, nombre, profe));
			}
			//h= gson.toJson(a);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	public String getGroupStudent(String Estudiante){
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOGruposEst> grupos= new ArrayList<VOGruposEst>();
		PreparedStatement preparedStmt = null;
		String query = "select * from estudiantes_grupos as e,  grupos as g where estudiante=? and e.codigoGrupo=g.codigo";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, Estudiante);
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String codeG = null;
			String est = null;
			String nota = null;
			String acces= null;
			String nombreg= null;
			while (rs.next()) {
				//code=rs.getString("codigo");
				code= rs.getString("codi");
				codeG= rs.getString("codigoGrupo");
				est = rs.getString("estudiante");
				nota = rs.getString("nota");
				nombreg=rs.getString("nombre");
				grupos.add(new VOGruposEst(code,codeG,est,nota,nombreg));
			}
			h= gson.toJson(grupos);
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	
	public String getGroupProfe(String profesor) {
		String h=null;
		Gson gson= new Gson();
		ArrayList<VOGrupos> grupos= new ArrayList<VOGrupos>();
		PreparedStatement preparedStmt = null;
		String query = "SELECT * FROM grupos where profesor = ?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, profesor);
			ResultSet rs = preparedStmt.executeQuery();
			String code=null;
			String acces= null;
			String nombre = null;
			String profe = null;
			while (rs.next()) {
				code=rs.getString("codigo");
				acces= rs.getString("AccessCode");
				nombre = rs.getString("nombre");
				profe = rs.getString("profesor");
				grupos.add(new VOGrupos(code,acces, nombre, profe));
			}
			h= gson.toJson(grupos);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return h;
	}
	public boolean isGroupProfe(String profesor) {
		boolean temp = false;
		PreparedStatement preparedStmt = null;
		String query = "select * from grupos where profesor = ?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, profesor);
			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				temp = true;
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public boolean isGroup(VOGrupos vo) {
		boolean temp = false;
		PreparedStatement preparedStmt = null;
		String query = "select * from grupos where profesor = ?";
		try {
			Connection connection = Conexion.getConenction();
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, (String)vo.getProfesor());
			ResultSet rs = preparedStmt.executeQuery();
			
			if (rs.next()) {
				temp = true;
			}
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

}

