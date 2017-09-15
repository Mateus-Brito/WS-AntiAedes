package com.example.antiaedes.entidades;

import java.io.Serializable;
import java.util.Date;

public class Visita implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id_fun;
	private int id_den;
	private String data;
	private int situacao;
	private String observacao;
	
	public Visita(){}
	
	public Visita(int id_fun, int id_den, String data, int situacao,String observacao) {
		this.id_fun = id_fun;
		this.id_den = id_den;
		this.data = data;
		this.situacao = situacao;
		this.observacao = observacao;
	}

	public int getId_fun() {
		return id_fun;
	}
	public void setId_fun(int id_fun) {
		this.id_fun = id_fun;
	}
	public int getId_den() {
		return id_den;
	}
	public void setId_den(int id_den) {
		this.id_den = id_den;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
