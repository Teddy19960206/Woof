package com.woof.administratorpermission.model;

import java.util.List;

import com.woof.cartlist.model.CartListVO;


public interface AdministratorPermissionDAO {
	void insert(AdministratorPermissionVO administratorPermissionVO);

	void update(AdministratorPermissionVO administratorPermissionVO);

	void delete(AdministratorPermissionVO administratorPermissionVO);

	AdministratorPermissionVO find(AdministratorPermissionVO administratorPermissionVO1);

	AdministratorPermissionVO findByAdminNo(Integer adminNo);
	


	List<AdministratorPermissionVO> getAll();
}
