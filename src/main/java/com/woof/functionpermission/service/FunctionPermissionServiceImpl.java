package com.woof.functionpermission.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.woof.functionpermission.dao.FunctionPermissionDAO;
import com.woof.functionpermission.dao.FunctionPermissionDAOImpl;
import com.woof.functionpermission.entity.FunctionPermission;
import com.woof.util.HibernateUtil;

public class FunctionPermissionServiceImpl implements FunctionPermissionService {
	private FunctionPermissionDAO dao;

	public FunctionPermissionServiceImpl() {
		dao = new FunctionPermissionDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public FunctionPermission addFunctionPermission(FunctionPermission functionPermission) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (dao.insert(functionPermission) == 1) {
			session.getTransaction().commit();
			return functionPermission;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public FunctionPermission updateFunctionPermission(FunctionPermission functionPermission) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (dao.update(functionPermission) == 1) {
			session.getTransaction().commit();
			return functionPermission;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public void deleteFunctionPermission(Integer funcNo) {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = HibernateUtil.getSessionFactory().getCurrentSession();
	        tx = session.beginTransaction();
	        FunctionPermission functionPermission = session.get(FunctionPermission.class, funcNo);
	        if (functionPermission != null) {
	            session.delete(functionPermission);
	        }
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        // Optionally: throw new SomeAppropriateException(e.getMessage(), e);
	    }
	}


	@Override
	public FunctionPermission findFunctionPermissionByFuncNo(Integer funcNo) {
		FunctionPermission functionPermission = dao.findByFuncNo(funcNo);
		return functionPermission;
	}

	@Override
	public List<FunctionPermission> getAllFunctionPermissions() {
		List<FunctionPermission> functionPermissionList = dao.getAll();
		return functionPermissionList;
	}

}
