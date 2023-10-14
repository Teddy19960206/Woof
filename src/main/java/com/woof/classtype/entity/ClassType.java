package com.woof.classtype.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.woof.groupcourse.entity.GroupCourse;

import javax.persistence.CascadeType;


@Entity
@Table(name="class_type")
public class ClassType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CT_NO" , updatable = false , columnDefinition = "TINYINT")
    private Integer ctNo;
	@Column(name = "CT_NAME" , nullable = false)
    private String ctName;
	
	@OneToMany(mappedBy = "classType" , cascade = CascadeType.ALL)
	private Set<GroupCourse> groupCourses;

    public Set<GroupCourse> getGroupCourses() {
		return groupCourses;
	}

	public void setGroupCourses(Set<GroupCourse> groupCourses) {
		this.groupCourses = groupCourses;
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
		ClassType other = (ClassType) obj;
		return Objects.equals(ctNo, other.ctNo);
	}


	@Override
    public String toString() {
        return "ClassType{" +
                "ctNo=" + ctNo +
                ", ctName='" + ctName + '\'' +
                '}';
    }
}
