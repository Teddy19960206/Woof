package com.woof.administratorpermission.dao;

import java.util.List;
import com.woof.administratorpermission.entity.AdministratorPermission;


//import idv.david.emp.entity.Emp;

public interface AdministratorPermissionDAO {
//	void insert(AdministratorPermissionVO administratorPermissionVO);
//
//	void update(AdministratorPermissionVO administratorPermissionVO);
//
//	void delete(AdministratorPermissionVO administratorPermissionVO);
//
//	AdministratorPermissionVO find(AdministratorPermissionVO administratorPermissionVO1);
//
//	AdministratorPermissionVO findByAdminNo(Integer adminNo);
//	
//
//
//	List<AdministratorPermissionVO> getAll();
	
	int insert(AdministratorPermission administratorPermissionVO);

	 int update(AdministratorPermission administratorPermissionVO);

	 int delete(Integer adminNo);
	

	 AdministratorPermission getById(Integer adminNo);

	 List<AdministratorPermission> getAll();

	 long getTotal();
	
}
