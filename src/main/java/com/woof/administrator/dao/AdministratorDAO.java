package com.woof.administrator.dao;

import java.util.List;
import java.util.Map;

import com.woof.administrator.entity.Administrator;
import com.woof.administratorpermission.entity.AdministratorPermission;

import com.woof.trainer.entity.Trainer;


public interface AdministratorDAO {

//    void insert(AdministratorVO administratorVO);
//
//    void update(AdministratorVO administratorVO);
//
//    void delete(AdministratorVO administratorVO);
//
//    AdministratorVO findbyAdminNo(Integer adminNO);
//
//    List<AdministratorVO> getAll();
	
	int insert(Administrator administrator);

	int update(Administrator administrator);

	int delete(Integer adminNo);

	List<Administrator> getAll();

	Administrator findByadminNo(Integer adminNo);
}

	
