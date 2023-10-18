package com.woof.functionpermission.service;

import java.util.List;

import com.woof.functionpermission.entity.FunctionPermission;

public interface FunctionPermissionService {
	FunctionPermission addFunctionPermission(FunctionPermission functionPermission);

	FunctionPermission updateFunctionPermission(FunctionPermission functionPermission);

	void deleteFunctionPermission(Integer funcNo);

	FunctionPermission findFunctionPermissionByFuncNo(Integer funcNo);

	List<FunctionPermission> getAllFunctionPermissions();

}
