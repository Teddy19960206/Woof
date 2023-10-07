package com.woof.classtype.model;

import java.io.Serializable;
import java.util.Objects;

public class ClassTypeVO implements Serializable {
    private Integer ctNo;
    private String ctName;


    public ClassTypeVO() {
    }


    public Integer getCtNo() {
        return ctNo;
    }

    public void setCtNo(Integer ctNo) {
        this.ctNo = ctNo;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassTypeVO classTypeVO = (ClassTypeVO) o;
        return Objects.equals(ctNo, classTypeVO.ctNo) && Objects.equals(ctName, classTypeVO.ctName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ctNo, ctName);
    }

    @Override
    public String toString() {
        return "ClassTypeVO{" +
                "ctNo=" + ctNo +
                ", ctName='" + ctName + '\'' +
                '}';
    }
}
