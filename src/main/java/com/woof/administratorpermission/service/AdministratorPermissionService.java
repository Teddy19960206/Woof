package com.woof.administratorpermission.service;

import java.util.List;

import com.woof.administratorpermission.entity.AdministratorPermission;



public interface AdministratorPermissionService {
	

	    int AddAdministratorPermission(String adminNo , Integer funcNo);

	    int updateAdministratorPermission(String adminNo , Integer funcNo);

	    int deleteAdministratorPermission(String adminNo , Integer funcNo);

	    List<AdministratorPermission> getAll();
	}

