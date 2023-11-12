package com.woof.administrator.service;

import java.util.List;

import com.woof.administrator.entity.Administrator;


public interface AdministratorService {

	Administrator addAdministrator(Administrator administrator);

	Administrator updateAdministrator(Administrator administrator);
	Administrator updateAdministrator2(Administrator administrator);

	void deleteAdministrator(String adminNo);

	Administrator findAdministratorByAdminNo(String adminNo);

	List<Administrator> getAllAdministrators();

	byte[] getPhotoById(String adminNo);


}
