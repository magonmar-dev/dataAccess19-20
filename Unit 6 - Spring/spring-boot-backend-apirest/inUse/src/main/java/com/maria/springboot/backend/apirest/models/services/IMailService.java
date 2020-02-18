package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import com.maria.springboot.backend.apirest.models.entity.Mail;

public interface IMailService {

public List<Mail> findAll();
	
	public Mail save(Mail mail);
	
	public Mail findById(Long id);
	
	public void delete(Long id);
}
