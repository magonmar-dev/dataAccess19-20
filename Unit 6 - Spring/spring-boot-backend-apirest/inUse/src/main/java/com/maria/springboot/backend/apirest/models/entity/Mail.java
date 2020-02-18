package com.maria.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "mails")
public class Mail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message="no es una dirección de correo bien formada")
	private String mail;
	
	@ManyToOne
	@JoinColumn(name="idusuario")
	@JsonManagedReference
	private Usuario usuario;
	
	public Mail() {}
	
	public Mail(String mail) {
		this.mail = mail;
	}
	
	public Mail(String mail, Usuario usuario) {
		this.mail = mail;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Mail [id=" + id + ", mail=" + mail + ", usuario=" + usuario + "]";
	}

	private static final long serialVersionUID = 6L;
}
