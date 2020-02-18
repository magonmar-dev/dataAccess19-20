package com.maria.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maria.springboot.backend.apirest.models.dao.IMailDAO;
import com.maria.springboot.backend.apirest.models.entity.Mail;

@Service
public class MailServiceImpl implements IMailService {
	
	@Autowired
	private IMailDAO mailDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Mail> findAll() {
		return (List<Mail>) mailDao.findAll();
	}

	@Override
	@Transactional
	public Mail save(Mail mail) {
		return mailDao.save(mail);
	}

	@Override
	@Transactional(readOnly = true)
	public Mail findById(Long id) {
		return mailDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		mailDao.deleteById(id);
	}
}
