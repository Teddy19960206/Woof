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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.insert(administrator) == 1) {
				session.getTransaction().commit();
				return administrator;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public Administrator updateAdministrator(Administrator administrator) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.update(administrator) == 1) {
				session.getTransaction().commit();
				return administrator;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public void deleteAdministrator(Integer adminNo) {
		
	}

	@Override
	public Administrator findAdministratorByAdminNo(Integer adminNo) {
		Administrator administrator = dao.findByadminNo(adminNo);
		return administrator;
		}

	@Override
	public List<Administrator> getAllAdministrators() {
			List<Administrator> administratorList = dao.getAll();
			return administratorList;
		}
	}



