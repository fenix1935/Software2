package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOCurso;

public class DAOCurso {

	public boolean addCurso(VOCurso t) throws SQLException {
		int result = 0;
		Connection connection = Conexion.getConenction();
		String query = "insert into grupos" + " values (0,?,?,?)";
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = connection.prepareStatement(query);
			preparedStmt.setString(1, t.getCodigo());
			preparedStmt.setString(2, t.getNombre());
			preparedStmt.setString(3, t.getProfesor());

			result = preparedStmt.executeUpdate();
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
