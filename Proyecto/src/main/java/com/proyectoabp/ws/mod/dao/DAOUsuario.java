package com.proyectoabp.ws.mod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proyectoabp.ws.mod.conexion.Conexion;
import com.proyectoabp.ws.rest.vo.VOUsuario;


public class DAOUsuario {
	public boolean addUser(VOUsuario t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConenction();
        String query = "insert into usuario" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getUsuario());
            preparedStmt.setString(2, t.getPassword());
            preparedStmt.setString(3, t.getTipo());

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
	
	public boolean isUser(VOUsuario vo) {
		boolean temp = false;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM usuario where nombreUser = ?";
        try {
            Connection connection = Conexion.getConenction();
            preparedStmt = connection.prepareStatement(query);            
            preparedStmt.setString(1, vo.getUsuario());
            ResultSet rs = preparedStmt.executeQuery();
            String usera = null;
            String password = null;
            String tipo = null;
            if (rs.getString("nombreUser")!=null) {
                temp = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
	}
	
	public VOUsuario isUserName(String name) {
		VOUsuario temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM usuario where nombreUser = ?";
        try {
            Connection connection = Conexion.getConenction();
            preparedStmt = connection.prepareStatement(query);            
            preparedStmt.setString(1, name);
            ResultSet rs = preparedStmt.executeQuery();
            String usera = null;
            String password = null;
            String tipo = null;
            if (rs.next()) {
                temp = new VOUsuario();

                usera = rs.getString("nombreUser");
                temp.setUsuario(usera);

                password = rs.getString("contrase");
                temp.setPassword(password);

                tipo = rs.getString("tipouser");
                temp.setTipo(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
	}
}
