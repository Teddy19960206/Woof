package com.woof.groupcoursescheduledetail.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GroupCourseScheduleDetail implements Serializable {
    private Integer gcsdNo;
    private Integer gcsNo;
    private Date classDate;

    public GroupCourseScheduleDetail() {
    }

    public Integer getGcsdNo() {
        return gcsdNo;
    }

    public void setGcsdNo(Integer gcsdNo) {
        this.gcsdNo = gcsdNo;
    }

    public Integer getGcsNo() {
        return gcsNo;
    }

    public void setGcsNo(Integer gcsNo) {
        this.gcsNo = gcsNo;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCourseScheduleDetail that = (GroupCourseScheduleDetail) o;
        return Objects.equals(gcsdNo, that.gcsdNo) && Objects.equals(gcsNo, that.gcsNo) && Objects.equals(classDate, that.classDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcsdNo, gcsNo, classDate);
    }

    @Override
    public String toString() {
        return "GroupCourseScheduleDetail{" +
                "gcsdNo=" + gcsdNo +
                ", gcsNo=" + gcsNo +
                ", classDate=" + classDate +
                '}';
    }
}
