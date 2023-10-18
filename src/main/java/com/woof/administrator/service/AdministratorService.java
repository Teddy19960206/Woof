package com.woof.administrator.service;

import java.util.List;

import com.woof.administrator.entity.Administrator;


public interface AdministratorService {

	Administrator addAdministrator(Administrator administrator);

	Administrator updateAdministrator(Administrator administrator);

	void deleteAdministrator(Integer adminNo);

	Administrator findAdministratorByAdminNo(Integer adminNo);

	List<Administrator> getAllAdministrators();


}
