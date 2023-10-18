package com.woof.administratorpermission.dao;

import java.util.List;
import com.woof.administratorpermission.entity.AdministratorPermission;
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
	
    void insert(AdministratorPermission administratorPermission);


    void delete(AdministratorPermission administratorPermission);

    AdministratorPermission find(AdministratorPermission administratorPermission);

    List<AdministratorPermission> findbyAdminNo(Integer AdminNo);

    List<AdministratorPermission> getAll();
	
}
