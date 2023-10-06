package com.woof.classtype.model;

import java.io.Serializable;
import java.util.Objects;

public class ClassType implements Serializable {
    private Integer ctNo;
    private String ctName;


    public ClassType() {
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
        ClassType classType = (ClassType) o;
        return Objects.equals(ctNo, classType.ctNo) && Objects.equals(ctName, classType.ctName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ctNo, ctName);
    }

    @Override
    public String toString() {
        return "ClassType{" +
                "ctNo=" + ctNo +
                ", ctName='" + ctName + '\'' +
                '}';
    }
}
