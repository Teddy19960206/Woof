package com.woof.administrator.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.woof.administrator.entity.Administrator;
import com.woof.member.entity.Member;
import com.woof.administrator.entity.Administrator;




//
public class AdministratorDAOImpl implements AdministratorDAO {
	private SessionFactory factory;

	public AdministratorDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(Administrator administrator) {
		getSession().save(administrator);
	}

	@Override
	public int update(Administrator administrator) {
		try {
			getSession().update(administrator);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	public int update2(Administrator administrator) {
		Administrator originalData  = findByadminNo(administrator.getAdminNo());
		
		try {
			//修改之前 先查出該管理員的原始資料
			//並把HD 和 Status放回 不接受前端參數修改
			//---------------------------------------
			administrator.setAdminHd(originalData.getAdminHd());
			administrator.setAdminRd(originalData.getAdminRd());
			administrator.setAdminStatus(originalData.getAdminStatus());
			administrator.setAdminVerifyStatus(originalData.getAdminVerifyStatus());
			administrator.setAdminFuncName(originalData.getAdminFuncName());
			getSession().merge(administrator);
			//---------------------------------------
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	@Override
	public int delete(String adminNo) {
		Administrator administrator =getSession().get(Administrator.class, adminNo);
		if(administrator !=null) {
			getSession().delete(administrator);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public Administrator findByadminNo(String adminNo) {
		Query<Administrator> query = getSession().createQuery("FROM Administrator where adminNo=:adminNo",Administrator.class);
		query.setParameter("adminNo", adminNo);
		return query.getSingleResult();
	}

	@Override
	public List<Administrator> getAll() {
		return getSession().createQuery("FROM Administrator", Administrator.class).list();
	}
	@Override
	public Administrator findAdministratorByEmail(String adminEmail) {
		
		String hql = "FROM Administrator admin WHERE admin.adminEmail = :adminEmail";
		Query query = getSession().createQuery(hql , Administrator.class);
		query.setParameter("adminEmail", adminEmail);
		
		return (Administrator) query.getSingleResult();
		
	}
}
	

