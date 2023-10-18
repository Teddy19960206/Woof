package com.woof.groupcourse.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.woof.classtype.entity.ClassType;
import com.woof.skill.entity.Skill;


@Entity
@Table(name = "group_course")
public class GroupCourse implements Serializable {

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GC_NO" , updatable = false , nullable = false)
    private Integer gcNo;

	@Expose
	@ManyToOne
	@JoinColumn(name = "SKILL_NO" , referencedColumnName = "SKILL_NO")
	private Skill skill;

	@Expose
	@ManyToOne
	@JoinColumn(name = "CT_NO" , referencedColumnName = "CT_NO")
	private ClassType classType;

	@Expose
	@Column(name = "COURSE_PHOTO" , columnDefinition= "MEDIUMBLOB")
    private byte[] coursePhoto;
	@Expose
	@Column(name = "COURSE_CONTENT")
    private String courseContent;

	@Expose
	@Column(name = "COURSE_STATUS" , nullable = false , insertable = false , columnDefinition = "TINYINT")
    private Integer courseStatus;

    public GroupCourse() {
    }


	public Integer getGcNo() {
        return gcNo;
    }

    public void setGcNo(Integer gcNo) {
        this.gcNo = gcNo;
    }

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}


	public ClassType getClassType() {
		return classType;
	}


	public void setClassType(ClassType classType) {
		this.classType = classType;
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
		GroupCourse other = (GroupCourse) obj;
		return Objects.equals(gcNo, other.gcNo);
	}


	@Override
	public String toString() {
		return "GroupCourse [gcNo=" + gcNo + ", coursePhoto=" + Arrays.toString(coursePhoto) + ", courseContent="
				+ courseContent + ", courseStatus=" + courseStatus + "]";
	}


	

}
