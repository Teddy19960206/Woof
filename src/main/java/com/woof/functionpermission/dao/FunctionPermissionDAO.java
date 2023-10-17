package com.woof.functionpermission.dao;

import java.util.*;

import com.woof.functionpermission.entity.FunctionPermission;

public interface FunctionPermissionDAO {
	int insert(FunctionPermission functionPermission);

	int update(FunctionPermission functionPermission);

	int delete(Integer funcNo);

	List<FunctionPermission> getAll();

	FunctionPermission findByFuncNo(Integer funcNo);

}
