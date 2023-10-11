package com.woof.classtype.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="class_type")
public class ClassTypeVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CT_NO" , updatable = false)
    private Integer ctNo;
	@Column(name = "CT_NAME")
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
