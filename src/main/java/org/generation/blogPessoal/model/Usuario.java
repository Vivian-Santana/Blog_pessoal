package org.generation.blogPessoal.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id; //ATRIBUTO 
	
	@NotNull     //PARA NÃO ACEITAR CAMPO NULO
	@Size (min= 2, max = 100)  //TAMANHO DO NOME
	private String nome; //ATRIBUTO
	
	@Schema(example = "email@email.com")
	@Size(min =5, max = 100) //TAMANHO DO E-MAIL DO USUÁRIO
	@NotNull(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email válido!")
	private String usuario; //ATRIBUTO
	
	@NotNull
	@Size(min =5) //TAMNAHO DA SENHA
	private String senha; //STRING PARA SENHA PQ SENHA TEM QUALQUER TIPO DE CARACTER
	
	@Size(max = 500, message = "O link da foto não pode ser maior do que 500 caractéres")
	private String foto;
	
	private String tipo; //TIPO DE USUÁRIO: ADMINISTRADOR OU USUÁRIO COMUM

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
		

	public Usuario(){  //CONSTRUTOR VAZIO QUE PUCHA A SUPER CLASSE PARA NÃO DAR ERRO DE LOGIN
		   super();
		}
	
	public Usuario(long id,  String nome, String foto, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.usuario = usuario;
		this.senha = senha;
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public String getUsuario() {
		return usuario;
	}


	public String getSenha() {
		return senha;
	}


	public String getFoto() {
		return foto;
	}


	public String getTipo() {
		return tipo;
	}


	public List<Postagem> getPostagem() {
		return postagem;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
