package com.maria.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "productos_clientes")
public class ProductoCliente implements Serializable {

	@EmbeddedId
	public ProductoClienteID id;
	
	@MapsId("codProducto")
	@ManyToOne
	@JoinColumn(name="cod_producto")
	@JsonManagedReference
	public Producto producto;
 
	@MapsId("idCliente")
    @ManyToOne
    @JoinColumn(name="id_cliente")
    @JsonManagedReference
    public Cliente cliente;

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

	private static final long serialVersionUID = 3L;
}
