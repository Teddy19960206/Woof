package com.woof.administratorpermission.service;

import java.util.List;
import com.woof.administratorpermission.entity.AdministratorPermission;
import com.woof.administratorpermission.dao.AdministratorPermissionDAO;
import com.woof.administratorpermission.dao.AdministratorPermissionDAOImpl;
import com.woof.administratorpermission.service.AdministratorPermissionService;
import com.woof.util.HibernateUtil;

public class AdministratorPermissionServiceImpl implements AdministratorPermissionService {
	 private AdministratorPermissionDAO dao;

	    public AdministratorPermissionServiceImpl(){
	        dao = new AdministratorPermissionDAOImpl(HibernateUtil.getSessionFactory());
	    }

	    @Override
	    public int AddAdministratorPermission(String adminNo, Integer funcNo) {

	    	AdministratorPermission administratorPermission = new AdministratorPermission();
	        administratorPermission.setAdminNo(Integer.parseInt(adminNo));
	        administratorPermission.setFuncNo(funcNo);

	        return dao.insert(administratorPermission);
	    }

	    @Override
	    public int updateAdministratorPermission(String adminNo, Integer funcNo) {
	        AdministratorPermission administratorPermission = new AdministratorPermission();
	        administratorPermission.setAdminNo(Integer.parseInt(adminNo));
	        administratorPermission.setFuncNo(funcNo);

	        return dao.update(administratorPermission);
	    }

	    @Override
	    public int  deleteAdministratorPermission(String adminNo, Integer funcNo) {
	        AdministratorPermission administratorPermission = new AdministratorPermission();
	        administratorPermission.setAdminNo(Integer.parseInt(adminNo));
	        administratorPermission.setFuncNo(funcNo);

	        return dao.delete(administratorPermission);
	    }

	    @Override
	    public List<AdministratorPermission> getAll() {
	        return dao.getAll();
	    }
	}
