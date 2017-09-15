package com.example.antiaedes.entidades;

import java.io.Serializable;

public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private boolean ativo;
	
	public Funcionario(){};
	
	public Funcionario(int id, String cpf, String nome, String email, String senha, boolean ativo) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
