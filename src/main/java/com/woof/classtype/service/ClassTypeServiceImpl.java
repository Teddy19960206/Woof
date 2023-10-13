package com.woof.classtype.service;

import java.util.List;

import com.woof.classtype.model.ClassTypeDAO;
import com.woof.classtype.model.ClassTypeDAOImpl;
import com.woof.classtype.model.ClassTypeVO;
import com.woof.util.HibernateUtil;
import org.hibernate.Session;

public class ClassTypeServiceImpl implements ClassTypeService{
	
	private ClassTypeDAO dao;
	
	public ClassTypeServiceImpl() {
		dao = new ClassTypeDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public ClassTypeVO addClassType(ClassTypeVO classType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassTypeVO updateClassType(ClassTypeVO classType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClassType(Integer ctNo) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public ClassTypeVO findClassTypeByNO(Integer ctNo) {
		return null;
	}

	@Override
	public List<ClassTypeVO> getAllClassTypes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<ClassTypeVO> classTypeVOList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return classTypeVOList;
	}

	
	
	

}
