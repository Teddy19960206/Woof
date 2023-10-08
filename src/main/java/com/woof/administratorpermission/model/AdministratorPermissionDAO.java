package com.woof.administratorpermission.model;

import java.util.List;

public interface AdministratorPermissionDAO {
	void insert(AdministratorPermissionVO administratorPermissionVO);

	void update(AdministratorPermissionVO administratorPermissionVO);

	void delete(AdministratorPermissionVO administratorPermissionVO);

	AdministratorPermissionVO findbyAdminNo(Integer adminNO);

	List<AdministratorPermissionVO> getAll();
}
