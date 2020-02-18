package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;

public interface IProductoClienteService {

	public List<ProductoCliente> findAll();
	
	public ProductoCliente save(ProductoCliente compra);
	
	public ProductoCliente findById(ProductoClienteID id);
	
	public void delete(ProductoClienteID id);
}
