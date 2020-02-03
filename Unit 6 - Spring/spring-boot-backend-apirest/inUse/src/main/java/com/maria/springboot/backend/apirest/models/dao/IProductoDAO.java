package com.maria.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.maria.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long> {

}
