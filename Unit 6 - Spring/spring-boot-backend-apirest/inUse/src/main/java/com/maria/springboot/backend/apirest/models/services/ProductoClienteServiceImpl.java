package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maria.springboot.backend.apirest.models.dao.IProductoClienteDAO;
import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;

@Service
public class ProductoClienteServiceImpl implements IProductoClienteService {

	@Autowired
	private IProductoClienteDAO productoClienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoCliente> findAll() {
		return (List<ProductoCliente>) productoClienteDao.findAll();
	}

	@Override
	@Transactional
	public ProductoCliente save(ProductoCliente productoCliente) {
		return productoClienteDao.save(productoCliente);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoCliente findById(ProductoClienteID id) {
		return productoClienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(ProductoClienteID id) {
		productoClienteDao.deleteById(id);
	}
}
