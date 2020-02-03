package com.maria.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productos_clientes")
public class ProductoCliente implements Serializable {

	@EmbeddedId
	private ProductoClienteID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("codProducto")
    private Producto producto;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCliente")
    private Cliente cliente;
    
    @JsonIgnore
    @Column(name="fecha_compra", insertable=false, updatable=false)
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;

	public ProductoClienteID getId() {
		return id;
	}

	public void setId(ProductoClienteID id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	private static final long serialVersionUID = 3L;
}
