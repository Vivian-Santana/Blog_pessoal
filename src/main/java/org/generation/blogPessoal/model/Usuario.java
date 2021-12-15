package org.generation.blogPessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id; //ATRIBUTO
	
	@NotNull     //PARA NÃO ACEITAR CAMPO NULO
	@Size (min= 2, max = 100)  //TAMANHO DO NOME
	private String nome; //ATRIBUTO
	
	@NotNull
	@Size(min =5, max = 100) //TAMANHO DO NOME USUARIO 
	private String usuario; //ATRIBUTO
	
	@NotNull
	@Size(min =5, max = 100) //TAMNAHO DA SENHA
	private String senha; //STRING PARA SENHA PQ SENHA TEM QUALQUER TIPO DE CARACTER

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
