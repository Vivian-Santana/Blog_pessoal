package org.generation.blogPessoal.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "postagem")

public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 5, max = 500)
	private String texto;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime data = LocalDateTime.now();
	

	@ManyToOne (cascade= CascadeType.REMOVE)
	@JoinColumn (name = "fk_tema")
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


	public LocalDateTime getData() {
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


	public void setData(LocalDateTime data) {
		this.data = data;
	}


	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
