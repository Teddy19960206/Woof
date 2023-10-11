package com.woof.groupcoursescheduledetail.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "group_course_schedule_detail")
public class GroupCourseScheduleDetailVO implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCSD_NO" , updatable = false)
    private Integer gcsdNo;
	
	@Column(name = "GCS_NO")
    private Integer gcsNo;
	
	@Column(name = "TRAINER_NO")
    private Integer trainerNo;
	
	@Column(name = "CLASS_DATE")
    private Date classDate;

    public GroupCourseScheduleDetailVO() {
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
    public Integer getTrainerNo() {
        return trainerNo;
    }

    public void setTrainerNo(Integer trainerNo) {
        this.trainerNo = trainerNo;
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
        GroupCourseScheduleDetailVO that = (GroupCourseScheduleDetailVO) o;
        return Objects.equals(gcsdNo, that.gcsdNo) && Objects.equals(gcsNo, that.gcsNo) && Objects.equals(trainerNo, that.trainerNo) && Objects.equals(classDate, that.classDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcsdNo, gcsNo, trainerNo, classDate);
    }

    @Override
    public String toString() {
        return "GroupCourseScheduleDetailVO{" +
                "gcsdNo=" + gcsdNo +
                ", gcsNo=" + gcsNo +
                ", trainerNo=" + trainerNo +
                ", classDate=" + classDate +
                '}';
    }
}
