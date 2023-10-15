package com.woof.administratorpermission.dao;

import java.util.List;
import java.util.Map;

import com.woof.administrator.entity.AdministratorVO;
import com.woof.administratorpermission.entity.AdministratorPermissionVO;
import com.woof.cartlist.model.CartListVO;
import com.woof.skillslist.model.SkillsListVO;

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
	
	int insert(AdministratorPermissionVO administratorPermissionVO);

	 int update(AdministratorPermissionVO administratorPermissionVO);

	 int delete(Integer adminNo);
	

	 AdministratorPermissionVO getById(Integer adminNo);

	 List<AdministratorPermissionVO> getAll();

	 long getTotal();
	
}
