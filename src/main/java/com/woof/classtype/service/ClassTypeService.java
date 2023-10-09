package com.woof.classtype.service;

import com.woof.classtype.model.ClassTypeDAO;
import com.woof.classtype.model.ClassTypeDAOImpl;
import com.woof.classtype.model.ClassTypeVO;

import java.util.List;

public class ClassTypeService {

    private ClassTypeDAO classTypeDAO;

    public ClassTypeService(){
        classTypeDAO = new ClassTypeDAOImpl();
    }

    public ClassTypeVO addClassType(String ctName){
        ClassTypeVO classTypeVO = new ClassTypeVO();

        classTypeVO.setCtName(ctName);
        classTypeDAO.insert(classTypeVO);

        return classTypeVO;
    }

    public ClassTypeVO updateClassType(Integer ctNo , String ctName){
        ClassTypeVO classTypeVO = new ClassTypeVO();

        classTypeVO.setCtNo(ctNo);
        classTypeVO.setCtName(ctName);
        classTypeDAO.update(classTypeVO);

        return classTypeDAO.findbyCtNo(ctNo);
    }

    public void deleteClassType(Integer ctNo){
        classTypeDAO.delete(ctNo);
    }

    public ClassTypeVO getOneClassType(Integer ctNo){
        return classTypeDAO.findbyCtNo(ctNo);
    }

    public List<ClassTypeVO> getAll(){
        return classTypeDAO.getAll();
    }
}
