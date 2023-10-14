package com.woof.classtype.dao;

import com.woof.classtype.entity.ClassType;

import java.util.List;

public interface ClassTypeDAO {

    int insert(ClassType classType);

    int update(ClassType classType);

    int delete(Integer ctno);

    ClassType findbyCtNo(Integer ctNo);

    List<ClassType> getAll();
}
