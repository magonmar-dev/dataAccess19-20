package com.maria.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;

public interface IProductoClienteDAO extends CrudRepository<ProductoCliente, ProductoClienteID> {

}
