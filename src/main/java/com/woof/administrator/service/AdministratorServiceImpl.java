package com.woof.administrator.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.administrator.dao.AdministratorDAO;
import com.woof.administrator.dao.AdministratorDAOImpl;
import com.woof.administrator.entity.Administrator;

import com.woof.util.HibernateUtil;

public class AdministratorServiceImpl implements AdministratorService {
	private AdministratorDAO dao;

	public AdministratorServiceImpl() {
		dao = new AdministratorDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public Administrator addAdministrator(Administrator administrator) {
		dao.insert(administrator);
		return administrator;
	}

	@Override
	public Administrator updateAdministrator(Administrator administrator) {
		int i = dao.update(administrator);
		if (i == 1) {
			return administrator;
		}
		return null;
	}

	@Override
	public void deleteAdministrator(String adminNo) {
		dao.delete(adminNo);
	}

	@Override
	public Administrator findAdministratorByAdminNo(String adminNo) {
		Administrator administrator = dao.findByadminNo(adminNo);
		return administrator;
	}

	@Override
	public List<Administrator> getAllAdministrators() {
		List<Administrator> administratorList = dao.getAll();
		return administratorList;
	}
}
