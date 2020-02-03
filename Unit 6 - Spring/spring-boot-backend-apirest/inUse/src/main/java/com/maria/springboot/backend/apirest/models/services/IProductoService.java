package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import com.maria.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public Producto findById(Long id);
	
	public void delete(Long id);
}
