package com.maria.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.maria.springboot.backend.apirest.models.entity.Mail;

public interface IMailDAO extends CrudRepository<Mail, Long>{

}
