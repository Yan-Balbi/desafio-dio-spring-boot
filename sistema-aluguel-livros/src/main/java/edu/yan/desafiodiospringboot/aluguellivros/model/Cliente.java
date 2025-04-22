package edu.yan.desafiodiospringboot.aluguellivros.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cpf;
	@Column(length = 100, nullable = false, name = "nome_usuario")
	private String nomeUsuario;
	@Column(length = 100, nullable = false)
	private String senha;
//	private List<Livro> livrosAlugados = new ArrayList<>();
	
	public Cliente(String cpf, String nomeUsuario, String senha) {
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
