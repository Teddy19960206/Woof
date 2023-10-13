package com.woof.classtype.model;

import java.util.List;

public interface ClassTypeDAO {

    int insert(ClassTypeVO classTypeVO);

    int update(ClassTypeVO classTypeVO);

    int delete(Integer ctno);

    ClassTypeVO findbyCtNo(Integer ctNo);

    List<ClassTypeVO> getAll();
}
