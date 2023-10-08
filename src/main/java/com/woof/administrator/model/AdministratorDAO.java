package com.woof.administrator.model;

import java.util.List;


public interface AdministratorDAO {

    void insert(AdministratorVO administratorVO);

    void update(AdministratorVO administratorVO);

    void delete(AdministratorVO administratorVO);

    AdministratorVO findbyAdminNo(Integer adminNO);

    List<AdministratorVO> getAll();
}