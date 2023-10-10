package com.woof.classtype.model;

import java.util.List;

public interface ClassTypeDAO {

    void insert(ClassTypeVO classTypeVO);

    void update(ClassTypeVO classTypeVO);

    void delete(Integer ctno);

    ClassTypeVO findbyCtNo(Integer ctNo);

    List<ClassTypeVO> getAll();
}
