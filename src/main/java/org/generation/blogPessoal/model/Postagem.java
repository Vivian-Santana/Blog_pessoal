package org.generation.blogPessoal.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //ANOTAÇÃO INDICANDO QUE A POSTAGEM É UMA ENTIDADE DO JPA HIBERNATE
@Table(name = "postagem") //INDICA QUE A ENTIDADE VAI CRIAR UAM TABELA DE NOME "POSTAGEM"

public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //INDICA QUE O ID VAI SER UMA CHAVE PRIMARIA (PRIMARY KEY)
	private long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 5, max = 500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) //INDICA O TEMPO PARA O JPA HIBERNATE
	private Date data = new java.sql.Date(System.currentTimeMillis()); //COLOCA A DATA E HORA DA POSTAGEM 
	
	@ManyToOne
	@JoinColumn(name = "fk_tema")
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}


	public String getTexto() {
		return texto;
	}

	public Date getData() {
		return data;
	}

	public Tema getTema() {
		return tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
