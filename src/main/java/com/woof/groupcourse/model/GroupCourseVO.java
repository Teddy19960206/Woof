package com.woof.groupcourse.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.woof.classtype.model.ClassTypeVO;
import com.woof.skill.model.SkillVO;


@Entity
@Table(name = "group_course")
public class GroupCourseVO implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GC_NO" , updatable = false , nullable = false)
    private Integer gcNo;
	
	@ManyToOne
	@JoinColumn(name = "SKILL_NO" , referencedColumnName = "SKILL_NO")
	private SkillVO skillVO;
	
	
	public SkillVO getSkillVO() {
		return skillVO;
	}


	public void setSkillVO(SkillVO skillVO) {
		this.skillVO = skillVO;
	}
	
	@ManyToOne
	@JoinColumn(name = "CLASS_TYPE" , referencedColumnName = "CLASS_TYPE")
	private ClassTypeVO classTypeVO;
	
	
	@Column(name = "COURSE_PHOTO" , columnDefinition= "MEDIUMBLOB")
    private byte[] coursePhoto;
	
	@Column(name = "COUSE_CONTENT")
    private String courseContent;
	
	@Column(name = "COURSE_STATUS" , nullable = false , insertable = false)
    private Integer courseStatus;

    public GroupCourseVO() {
    }


    public Integer getGcNo() {
        return gcNo;
    }

    public void setGcNo(Integer gcNo) {
        this.gcNo = gcNo;
    }

    

    public ClassTypeVO getClassTypeVO() {
		return classTypeVO;
	}


	public void setClassTypeVO(ClassTypeVO classTypeVO) {
		this.classTypeVO = classTypeVO;
	}


	public byte[] getCoursePhoto() {
        return coursePhoto;
    }

    public void setCoursePhoto(byte[] coursePhoto) {
        this.coursePhoto = coursePhoto;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }


	@Override
	public int hashCode() {
		return Objects.hash(gcNo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupCourseVO other = (GroupCourseVO) obj;
		return Objects.equals(gcNo, other.gcNo);
	}


	@Override
	public String toString() {
		return "GroupCourseVO [gcNo=" + gcNo + ", coursePhoto=" + Arrays.toString(coursePhoto) + ", courseContent="
				+ courseContent + ", courseStatus=" + courseStatus + "]";
	}


	

}
