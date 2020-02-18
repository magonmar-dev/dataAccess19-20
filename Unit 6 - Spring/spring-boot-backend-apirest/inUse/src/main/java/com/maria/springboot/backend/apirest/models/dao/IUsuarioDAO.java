package com.maria.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.maria.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

}
