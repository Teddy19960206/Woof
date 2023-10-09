package com.woof.functionpremission.model;

import java.util.*;

public interface FunctionPermissionDAO {
	public void insert(FunctionPermissionVO functionPermissionVO);

	public void update(FunctionPermissionVO functionPermissionVO);

	public void delete(FunctionPermissionVO functionPermissionVO);

	public FunctionPermissionVO findBy(Integer funcNo);

	public List<FunctionPermissionVO> getAll();

}
