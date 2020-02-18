package com.maria.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacío")
	@Column(nullable=false)
	private String name;
	
	@NotEmpty(message="no puede estar vacío")
	@Column(nullable=false)
	private String password;
	
	@OneToOne
	@JoinColumn(
		name="idcliente",
		referencedColumnName = "id",
		unique=true,
		nullable=false
	)
	private Cliente cliente;
	
	@OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
	)
	@JsonBackReference
	private Set<Mail> mails = new HashSet<Mail>();
	
	public Usuario() {}

	public Usuario(Long id, String name, String password, Cliente cliente, Set<Mail> mails) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.cliente = cliente;
		this.mails = mails;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Set<Mail> getMails() {
		return mails;
	}

	public void setMails(Set<Mail> mails) {
		this.mails = mails;
	}

	private static final long serialVersionUID = 5L;
}