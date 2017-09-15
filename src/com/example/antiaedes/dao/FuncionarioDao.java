package com.example.antiaedes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.antiaedes.ConnectionFactory;
import com.example.antiaedes.entidades.Funcionario;

public class FuncionarioDao {

	public Funcionario login(String email, String password){
		Funcionario funcionario = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		String queryInsert = "SELECT * FROM funcionario WHERE email = ? AND senha = ? LIMIT 1";
		
		try {
			preparedStatement = conn.prepareStatement(queryInsert);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt("id"));
				funcionario.setNome(resultSet.getString("nome"));
				funcionario.setEmail(resultSet.getString("email"));
				funcionario.setCpf(resultSet.getString("cpf"));
				funcionario.setAtivo(resultSet.getBoolean("ativo"));
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
		return funcionario;
	}
}
