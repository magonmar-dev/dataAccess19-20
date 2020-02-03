package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;

public interface IProductoClienteService {

public List<ProductoCliente> findAll();
	
	public ProductoCliente save(ProductoCliente cliente);
	
	public ProductoCliente findById(ProductoClienteID id);
	
	public void delete(ProductoClienteID id);
}
