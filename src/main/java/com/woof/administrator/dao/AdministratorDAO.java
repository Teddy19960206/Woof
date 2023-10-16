package com.woof.administrator.dao;

import java.util.List;
import java.util.Map;

import com.woof.administrator.entity.AdministratorVO;
import com.woof.administratorpermission.entity.AdministratorPermission;


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
	int insert(AdministratorVO administratorVO);

	 int update(AdministratorVO administratorVO);

	 int delete(Integer adminNo);
	 List<AdministratorVO> getByCompositeQuery(Map<String, String> map);

	 AdministratorVO getById(Integer adminNo);

	 List<AdministratorVO> getAll();

	 long getTotal();

	}