package com.woof.groupcourse.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "group_course")
public class GroupCourseVO implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GC_NO" , updatable = false , nullable = false)
    private Integer gcNo;
	
	@Column(name = "SKILL_NO" , nullable = false)
    private Integer skillNo;
	
	@Column(name = "CLASS_TYPE" , nullable = false)
    private Integer classType;
	
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

    public Integer getSkillNo() {
        return skillNo;
    }

    public void setSkillNo(Integer skillNo) {
        this.skillNo = skillNo;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCourseVO that = (GroupCourseVO) o;
        return Objects.equals(gcNo, that.gcNo) && Objects.equals(skillNo, that.skillNo) && Objects.equals(classType, that.classType) && Arrays.equals(coursePhoto, that.coursePhoto) && Objects.equals(courseContent, that.courseContent) && Objects.equals(courseStatus, that.courseStatus);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(gcNo, skillNo, classType, courseContent, courseStatus);
        result = 31 * result + Arrays.hashCode(coursePhoto);
        return result;
    }

    @Override
    public String toString() {
        return "GroupCourseVO{" +
                "gcNo=" + gcNo +
                ", skillNo=" + skillNo +
                ", classType=" + classType +
                ", coursePhoto=" + Arrays.toString(coursePhoto) +
                ", courseContent='" + courseContent + '\'' +
                ", courseStatus=" + courseStatus +
                '}';
    }

}
