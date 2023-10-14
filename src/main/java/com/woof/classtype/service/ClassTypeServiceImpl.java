package com.woof.classtype.service;

import java.util.List;

import com.woof.classtype.dao.ClassTypeDAO;
import com.woof.classtype.dao.ClassTypeDAOImpl;
import com.woof.classtype.entity.ClassType;
import com.woof.util.HibernateUtil;
import org.hibernate.Session;

public class ClassTypeServiceImpl implements ClassTypeService{
	
	private ClassTypeDAO dao;
	
	public ClassTypeServiceImpl() {
		dao = new ClassTypeDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public ClassType addClassType(ClassType classType) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(classType) == 1){

			session.getTransaction().commit();
			return classType;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public ClassType updateClassType(ClassType classType) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(classType);
		if (result == 1){

			session.getTransaction().commit();
			return classType;
		}
		session.getTransaction().rollback();

		return null;
	}

	@Override
	public void deleteClassType(Integer ctNo) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public ClassType findClassTypeByNO(Integer ctNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();



		return null;
	}

	@Override
	public List<ClassType> getAllClassTypes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<ClassType> classTypeList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return classTypeList;
	}

	
	
	

}
