package com.woof.functionpermission.dao;

import java.util.*;

import com.woof.functionpermission.entity.FunctionPermissionVO;

public interface FunctionPermissionDAO {
	int insert(FunctionPermissionVO functionPermissionVO);

	int update(FunctionPermissionVO functionPermissionVO);

	int delete(Integer funcNo);

	FunctionPermissionVO findbyfuncNo(Integer funcNo);

	List<FunctionPermissionVO> getAll();

}
