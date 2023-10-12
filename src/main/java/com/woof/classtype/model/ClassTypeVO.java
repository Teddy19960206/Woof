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
@Table(name="CLASS_TYPE")
public class ClassTypeVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CT_NO" , updatable = false , columnDefinition = "TINYINT")
    private Integer ctNo;
	@Column(name = "CT_NAME" , nullable = false)
    private String ctName;

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
	public int hashCode() {
		return Objects.hash(ctNo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassTypeVO other = (ClassTypeVO) obj;
		return Objects.equals(ctNo, other.ctNo);
	}


	@Override
    public String toString() {
        return "ClassTypeVO{" +
                "ctNo=" + ctNo +
                ", ctName='" + ctName + '\'' +
                '}';
    }
}
