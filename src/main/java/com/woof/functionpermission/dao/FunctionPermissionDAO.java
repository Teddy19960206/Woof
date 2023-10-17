package com.woof.functionpermission.dao;

import java.util.*;

import com.woof.functionpermission.entity.FunctionPermission;

public interface FunctionPermissionDAO {
	int insert(FunctionPermission functionPermissionVO);

	int update(FunctionPermission functionPermissionVO);

	int delete(Integer funcNo);

	FunctionPermission findbyfuncNo(Integer funcNo);

	List<FunctionPermission> getAll();

}
