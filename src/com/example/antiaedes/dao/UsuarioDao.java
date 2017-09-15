package com.example.antiaedes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.antiaedes.ConnectionFactory;
import com.example.antiaedes.entidades.Usuario;

public class UsuarioDao {
	
	public boolean registerUser(Usuario usuario){
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		String queryInsert = "INSERT INTO usuario() VALUES(null,?,?,?,?,?)";
		
		try {
			preparedStatement = conn.prepareStatement(queryInsert);
			preparedStatement.setString(1, usuario.getNome());//name
			preparedStatement.setString(2, usuario.getEmail());//email
			preparedStatement.setString(3, usuario.getSenha());//password
			preparedStatement.setInt(4, 0);//coins
			preparedStatement.setBoolean(5, true);//account is active
			int value = preparedStatement.executeUpdate();
			return value > 0 ? true:false; //Result Insert
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean diminuirSaldo(int userid,int saldo){
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String validaSaldo = "SELECT saldo FROM usuario WHERE id = ?";
		String queryInsert = "UPDATE usuario set SALDO=SALDO-? WHERE id=?";
		int value2 = 0;
		try {
			preparedStatement = conn.prepareStatement(validaSaldo);
			preparedStatement2 = conn.prepareStatement(queryInsert);
			
			preparedStatement.setInt(1, userid);//name
			ResultSet value = preparedStatement.executeQuery();
			if(value.getInt("saldo")>2){
				preparedStatement2.setInt(1, saldo);//email
				preparedStatement2.setInt(2, userid);//password
				value2 = preparedStatement2.executeUpdate();
				
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				preparedStatement2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value2 > 0 ? true:false; //Result Insert
	}

	public boolean aumentarSaldo(int userid,int saldo){
		Connection conn = ConnectionFactory.getConnection();

		PreparedStatement preparedStatement2 = null;
		String queryInsert = "UPDATE usuario set SALDO=SALDO+? WHERE id=?";
		int value2 = 0;
		try {

			preparedStatement2 = conn.prepareStatement(queryInsert);
			preparedStatement2.setInt(1, saldo);//email
			preparedStatement2.setInt(2, userid);//password
			value2 = preparedStatement2.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value2 > 0 ? true:false; //Result Insert
	}

	public Usuario login(String email, String password){
		Usuario usuario = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		String queryInsert = "SELECT * FROM usuario WHERE email = ? AND senha = ? LIMIT 1";
		
		try {
			preparedStatement = conn.prepareStatement(queryInsert);
			preparedStatement.setString(1, email);//name
			preparedStatement.setString(2, password);//email
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				usuario = new Usuario();
				usuario.setId(resultSet.getInt("id"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setAtivo(resultSet.getBoolean("ativo"));
				usuario.setSaldo(resultSet.getInt("saldo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
}
