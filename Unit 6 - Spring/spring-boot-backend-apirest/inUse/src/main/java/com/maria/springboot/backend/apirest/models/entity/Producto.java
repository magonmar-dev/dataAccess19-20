package com.maria.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codproducto;
	
	@NotEmpty(message="no puede estar vacía")
	@Column(nullable=false)
	private String descripcion;
	
	private Float precio;
	
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAlta;
	
	private Boolean disponibilidad;
	
	@OneToMany(
        mappedBy = "producto",
        cascade = CascadeType.ALL,
        orphanRemoval = true
	)
	@JsonBackReference
    private Set<ProductoCliente> compras = new HashSet<>();

	public Long getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(Long codproducto) {
		this.codproducto = codproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	public Set<ProductoCliente> getCompras() {
		return compras;
	}

	public void setCompras(Set<ProductoCliente> compras) {
		this.compras = compras;
	}
	
	@Override
	public String toString() {
		return "Producto [codproducto=" + codproducto + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", fechaAlta=" + fechaAlta + ", disponibilidad=" + disponibilidad + ", compras=" + compras + "]";
	}

	private static final long serialVersionUID = 2L;
}
