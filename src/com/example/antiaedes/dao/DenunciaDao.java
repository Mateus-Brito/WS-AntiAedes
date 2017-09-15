package com.example.antiaedes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.antiaedes.ConnectionFactory;
import com.example.antiaedes.entidades.Denuncia;


public class DenunciaDao {

	public ArrayList<Denuncia> getDenunciationsActives(){
		Connection conn = ConnectionFactory.getConnection();
		String query = "select d.id,d.cep,d.bairro,d.rua,d.cidade,d.referencia,d.descricao,d.num_casa,d.latitude,d.longitude,d.data,d.tipo,d.imagem,d.codigo,d.prioridade,d.id_user,d.id_fun,u.saldo as saldo from denuncia d,usuario u where d.id_user=u.id and d.id NOT IN ( SELECT id_den FROM visita WHERE situacao=0 or situacao=2) UNION select d.id,d.cep,d.bairro,d.rua,d.cidade,d.referencia,d.descricao,d.num_casa,d.latitude,d.longitude,d.data,d.tipo,d.imagem,d.codigo,d.prioridade,d.id_user,d.id_fun,u.saldo as saldo from denuncia d,usuario u where u.saldo=0 and d.id NOT IN ( SELECT id_den FROM visita WHERE situacao=0 or situacao=2) order by saldo DESC;";
		PreparedStatement prepared;
		ResultSet resultSet;
		UsuarioDao userdao = new UsuarioDao();
		ArrayList<Denuncia> denuncias = new ArrayList<>();
		try {
			prepared = conn.prepareStatement(query);
			resultSet = prepared.executeQuery();
			while(resultSet.next()){
				Denuncia denuncia = new Denuncia();
				denuncia.setId(resultSet.getInt("id"));
				denuncia.setCep(resultSet.getString("cep"));
				denuncia.setBairro(resultSet.getString("bairro"));
				denuncia.setRua(resultSet.getString("rua"));
				denuncia.setCidade(resultSet.getString("cidade"));
				denuncia.setReferencia(resultSet.getString("referencia"));
				denuncia.setDescricao(resultSet.getString("descricao"));
				denuncia.setNum_casa(resultSet.getInt("num_casa"));
				denuncia.setLatitude(resultSet.getString("latitude"));
				denuncia.setLongitude(resultSet.getString("longitude"));
				denuncia.setData(resultSet.getString("data"));
				denuncia.setTipo(resultSet.getInt("tipo"));
				denuncia.setImagem(resultSet.getString("imagem"));
				denuncia.setCodigo(resultSet.getInt("codigo"));
				denuncia.setPrioridade(resultSet.getBoolean("prioridade"));
				
				if(resultSet.getString("id_fun")!=null)
					denuncia.setId_fun(Integer.parseInt(resultSet.getString("id_fun")));
				if(resultSet.getString("id_user")!=null)
					denuncia.setId_user(Integer.parseInt(resultSet.getString("id_user")));
				denuncias.add(denuncia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return denuncias;
	}
	
	public ArrayList<Denuncia> getAllDenunciations(){
		Connection conn = ConnectionFactory.getConnection();
		String query = "select d.id,d.cep,d.bairro,d.rua,d.cidade,d.referencia,d.descricao,d.num_casa,d.latitude,d.longitude,d.data,d.tipo,d.imagem,d.codigo,d.prioridade,d.id_user,d.id_fun,u.saldo as saldo from denuncia d,usuario u where d.id_user=u.id UNION select d.id,d.cep,d.bairro,d.rua,d.cidade,d.referencia,d.descricao,d.num_casa,d.latitude,d.longitude,d.data,d.tipo,d.imagem,d.codigo,d.prioridade,d.id_user,d.id_fun,u.saldo as saldo from denuncia d,usuario u where u.saldo=0 order by saldo DESC;";
		PreparedStatement prepared;
		ResultSet resultSet;
		ArrayList<Denuncia> denuncias = new ArrayList<>();
		try {
			prepared = conn.prepareStatement(query);
			resultSet = prepared.executeQuery();
			while(resultSet.next()){
				Denuncia denuncia = new Denuncia();
				denuncia.setId(resultSet.getInt("id"));
				denuncia.setCep(resultSet.getString("cep"));
				denuncia.setBairro(resultSet.getString("bairro"));
				denuncia.setRua(resultSet.getString("rua"));
				denuncia.setCidade(resultSet.getString("cidade"));
				denuncia.setReferencia(resultSet.getString("referencia"));
				denuncia.setDescricao(resultSet.getString("descricao"));
				denuncia.setNum_casa(resultSet.getInt("num_casa"));
				denuncia.setLatitude(resultSet.getString("latitude"));
				denuncia.setLongitude(resultSet.getString("longitude"));
				denuncia.setData(resultSet.getString("data"));
				denuncia.setTipo(resultSet.getInt("tipo"));
				denuncia.setImagem(resultSet.getString("imagem"));
				denuncia.setCodigo(resultSet.getInt("codigo"));
				denuncia.setPrioridade(resultSet.getBoolean("prioridade"));
				if(resultSet.getString("id_fun")!=null)
					denuncia.setId_fun(Integer.parseInt(resultSet.getString("id_fun")));
				if(resultSet.getString("id_user")!=null)
					denuncia.setId_user(Integer.parseInt(resultSet.getString("id_user")));
				denuncias.add(denuncia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return denuncias;
	}
	
	public boolean registerDenunciation(Denuncia denuncia){
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		String queryInsert = "INSERT INTO denuncia() VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		UsuarioDao userdao = new UsuarioDao();
		try {
			preparedStatement = conn.prepareStatement(queryInsert);
			preparedStatement.setString(1, denuncia.getCep());
			preparedStatement.setString(2, denuncia.getBairro());
			preparedStatement.setString(3, denuncia.getRua());
			preparedStatement.setString(4, denuncia.getCidade());
			preparedStatement.setString(5, denuncia.getReferencia());
			preparedStatement.setString(6, denuncia.getDescricao());
			preparedStatement.setInt(7, denuncia.getNum_casa());
			preparedStatement.setString(8, denuncia.getLatitude());
			preparedStatement.setString(9, denuncia.getLongitude());
			preparedStatement.setString(10, denuncia.getData());
			preparedStatement.setInt(11, denuncia.getTipo());
			preparedStatement.setString(12, denuncia.getImagem());
			preparedStatement.setInt(13, denuncia.getCodigo());
			preparedStatement.setBoolean(14, denuncia.isPrioridade());
			if(denuncia.getId_user() != 0)
				preparedStatement.setInt(15, denuncia.getId_user());
			else
				preparedStatement.setNull(15, denuncia.getId_user());
			if(denuncia.getId_fun() != 0)
				preparedStatement.setInt(16, denuncia.getId_fun());
			else
				preparedStatement.setNull(16, denuncia.getId_fun());
			if(denuncia.isPrioridade()){
				userdao.diminuirSaldo(denuncia.getId_user(), 2);
			}
			System.out.println(preparedStatement.toString());
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
	
	public Denuncia buscarDenunciaPorId(int id){

		Denuncia denuncia = null;
		PreparedStatement prepared = null;
		ResultSet resultSet = null;
		Connection c = ConnectionFactory.getConnection();
		String queryBuscar = "SELECT * FROM denuncia WHERE id = ?";
		try {
			prepared = c.prepareStatement(queryBuscar);
			prepared.setInt(1, id);
			resultSet = prepared.executeQuery();

			if(resultSet.next()){
				String cep = resultSet.getString("cep");
				String bairro = resultSet.getString("bairro");
				String rua = resultSet.getString("rua");
				String cidade = resultSet.getString("cidade");
				String referencia = resultSet.getString("referencia");
				String descricao = resultSet.getString("descricao");
				int num_casa = resultSet.getInt("num_casa");
				String latitude = resultSet.getString("latitude");
				String longitude = resultSet.getString("longitude");
				String data_denuncia = resultSet.getString("data");
				int tipo = resultSet.getInt("tipo");
				String imagem = resultSet.getString("imagem");
				int codigo = resultSet.getInt("codigo");
				boolean prioridade  = resultSet.getBoolean("prioridade");
				int id_user = resultSet.getInt("id_user");
				int id_fun = resultSet.getInt("id_fun");
				denuncia = new Denuncia(id,cep,bairro,rua,cidade,referencia,descricao,num_casa,latitude
						,longitude,data_denuncia, tipo,imagem,codigo,prioridade,id_user,id_fun);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				prepared.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão!");
			}
		}
		return denuncia;
	}
}
