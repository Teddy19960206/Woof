package com.woof.classtype.model;

import java.util.List;

public interface ClassTypeDAO {

    void insert(ClassType classType);

    void update(ClassType classType);

    void delete(ClassType classType);

    ClassType findbyCtNo(Integer ctNo);

    List<ClassType> getAll();
}
