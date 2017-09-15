package com.example.antiaedes.entidades;

import java.io.Serializable;
import java.util.Date;

public class Denuncia implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String cep;
	private String bairro;
	private String rua;
	private String cidade;
	private String referencia;
	private String descricao;
	private int num_casa;
	private String latitude;
	private String longitude;
	private String data;
	private int tipo;
	private String imagem;
	private int codigo;
	private int id_user;
	private int id_fun;
	private boolean prioridade;

	public Denuncia() {
	};

	public Denuncia(int id, String cep, String bairro, String rua, String cidade, String referencia, String descricao, int num_casa,
			String latitude, String longitude, String data, int tipo, String imagem, int codigo,
			boolean prioridade, int id_user, int id_fun) {
		this.id = id;
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.cidade = cidade;
		this.referencia = referencia;
		this.descricao = descricao;
		this.num_casa = num_casa;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
		this.tipo = tipo;
		this.imagem = imagem;
		this.codigo = codigo;
		this.prioridade = prioridade;
		this.id_user = id_user;
		this.id_fun = id_fun;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNum_casa() {
		return num_casa;
	}

	public void setNum_casa(int num_casa) {
		this.num_casa = num_casa;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_fun() {
		return id_fun;
	}

	public void setId_fun(int id_fun) {
		this.id_fun = id_fun;
	}

	public boolean isPrioridade() {
		return prioridade;
	}

	public void setPrioridade(boolean prioridade) {
		this.prioridade = prioridade;
	}

}
