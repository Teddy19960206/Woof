package com.woof.administrator.dao;

import java.util.List;
import java.util.Map;

import com.woof.administrator.entity.Administrator;
import com.woof.administratorpermission.entity.AdministratorPermission;

import com.woof.trainer.entity.Trainer;


public interface AdministratorDAO {


	void insert(Administrator administrator);

	int update(Administrator administrator);
	int update2(Administrator administrator);

	int delete(String adminNo);

	List<Administrator> getAll();
	Administrator findAdministratorByEmail(String adminEmail);

	Administrator findByadminNo(String adminNo);
}

	
