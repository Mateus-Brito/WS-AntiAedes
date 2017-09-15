package com.example.antiaedes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.antiaedes.ConnectionFactory;
import com.example.antiaedes.entidades.Denuncia;
import com.example.antiaedes.entidades.Visita;
import com.mysql.jdbc.Statement;

public class VisitaDao {
	
	public boolean registerVisit(Visita visita){
		Connection conn = ConnectionFactory.getConnection();
		String queryVisit = "INSERT INTO visita() VALUES(?,?,?,?,?)";
		PreparedStatement insertVisit;
		DenunciaDao denunciaDao = new DenunciaDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		try {
			insertVisit = conn.prepareStatement(queryVisit);
			insertVisit.setInt(1, visita.getId_fun());
			insertVisit.setInt(2, visita.getId_den());
			insertVisit.setString(3, visita.getData());
			insertVisit.setInt(4, visita.getSituacao());
			insertVisit.setString(5, visita.getObservacao());
			int i = insertVisit.executeUpdate();

			Denuncia den = denunciaDao.buscarDenunciaPorId(visita.getId_den());
			if(den.getId_user() != 0){
				if(visita.getSituacao() != 2)
					usuarioDao.aumentarSaldo(den.getId_user(), 1);
				else
					usuarioDao.diminuirSaldo(den.getId_user(), 1);
			}
			if(i > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Visita> getVisits(String cep, int num_house){
		ArrayList<Visita> visits = new ArrayList<>();
		System.out.println(cep+" - "+num_house);
		Connection conn = ConnectionFactory.getConnection();
		String all = "SELECT * FROM denuncia WHERE cep =? and num_casa=?";
		String query = "SELECT * FROM visita WHERE id_den=?";
		PreparedStatement prepared;
		PreparedStatement prepared2;
		ResultSet resultSet;
		ResultSet resultSet2;
		try {
			prepared = conn.prepareStatement(all);
			prepared.setString(1, cep);
			prepared.setInt(2, num_house);
			resultSet = prepared.executeQuery();
			while(resultSet.next()){
				prepared2 = conn.prepareStatement(query);
				prepared2.setInt(1, resultSet.getInt("id"));
				System.out.println(prepared2.toString());
				resultSet2 = prepared2.executeQuery();
				while(resultSet2.next()){
					Visita visita = new Visita();
					visita.setId_den(resultSet2.getInt("id_den"));
					visita.setId_fun(resultSet2.getInt("id_fun"));
					visita.setData(resultSet2.getString("data"));
					visita.setObservacao(resultSet2.getString("observacao"));
					visita.setSituacao(resultSet2.getInt("situacao"));
					visits.add(visita);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visits;
	}

	public boolean registerVisit2(Denuncia denuncia, Visita visita){
		Connection conn = ConnectionFactory.getConnection();
		String queryVisit = "INSERT INTO visita() VALUES(?,?,?,?,?)";
		String queryDen = "INSERT INTO denuncia() VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertVisit;
		PreparedStatement searchDen;
		PreparedStatement insertDen;
		try {
			conn.setAutoCommit(false); //transaction is block
			insertDen = conn.prepareStatement(queryDen,Statement.RETURN_GENERATED_KEYS);
			insertDen.setString(1, denuncia.getCep());
			insertDen.setString(2, denuncia.getBairro());
			insertDen.setString(3, denuncia.getRua());
			insertDen.setString(4, denuncia.getCidade());
			insertDen.setString(5, denuncia.getReferencia());
			insertDen.setString(6, denuncia.getDescricao());
			insertDen.setInt(7, denuncia.getNum_casa());
			if(!denuncia.getLatitude().isEmpty() && !denuncia.getLongitude().isEmpty()){
				insertDen.setString(8, denuncia.getLatitude());
				insertDen.setString(9, denuncia.getLongitude());
			}else{
				insertDen.setNull(8, 0);
				insertDen.setNull(9, 0);
			}
			insertDen.setString(10, denuncia.getData());
			insertDen.setInt(11, denuncia.getTipo());
			insertDen.setString(12, denuncia.getImagem());
			insertDen.setInt(13, denuncia.getCodigo());
			insertDen.setBoolean(14, denuncia.isPrioridade());
			if(denuncia.getId_user() != 0)
				insertDen.setInt(15, denuncia.getId_user());
			else
				insertDen.setNull(15, denuncia.getId_user());
			if(denuncia.getId_fun() != 0)
				insertDen.setInt(16, denuncia.getId_fun());
			else
				insertDen.setNull(16, denuncia.getId_fun());
			System.out.println(insertDen.toString());
			insertDen.executeUpdate();
			
			int id_den = 0;
			
			ResultSet generatedKeys = insertDen.getGeneratedKeys();
			if (generatedKeys.next()) {
				id_den = generatedKeys.getInt(1);
			}
			insertVisit = conn.prepareStatement(queryVisit);
			insertVisit.setInt(1, visita.getId_fun());
			insertVisit.setInt(2, id_den);
			insertVisit.setString(3, visita.getData());
			insertVisit.setInt(4, visita.getSituacao());
			insertVisit.setString(5, visita.getObservacao());
			System.out.println(insertVisit.toString());
			insertVisit.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
