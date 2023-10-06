package com.woof.groupcourse.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class GroupCourse implements Serializable {

    private Integer gcNo;
    private Integer skillNo;
    private Integer classType;
    private byte[] coursePhoto;
    private String courseContent;
    private Integer courseStatus;

    public GroupCourse() {
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
        GroupCourse that = (GroupCourse) o;
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
        return "GroupCourse{" +
                "gcNo=" + gcNo +
                ", skillNo=" + skillNo +
                ", classType=" + classType +
                ", coursePhoto=" + Arrays.toString(coursePhoto) +
                ", courseContent='" + courseContent + '\'' +
                ", courseStatus=" + courseStatus +
                '}';
    }

}
