package com.woof.groupcourseschedule.model;

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
@Table(name = "group_course_schedule")
public class GroupCourseScheduleVO implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCS_NO" , updatable = false)
    private Integer gcsNo;
	
	@Column(name = "GC_NO")
    private Integer gcNo;
	
	@Column(name = "TRAINER_NO")
    private Integer trainerNo;
	
	@Column(name = "GCS_START")
    private Date gcsStart;
	
	@Column(name = "GCS_END")
    private Date gcsEnd;
	
	@Column(name = "MIN_LIMIT")
    private Integer minLimit;
	
	@Column(name = "MAX_LIMIT")
    private Integer maxLimit;
	
	@Column(name = "COUNT")
    private Integer count;
	
	@Column(name = "GCS_PRICE")
    private Integer gcsPrice;
	
	@Column(name = "GCS_STATUS")
    private Integer gcsStatus;

    public GroupCourseScheduleVO() {
    }

    public Integer getGcsNo() {
        return gcsNo;
    }

    public void setGcsNo(Integer gcsNo) {
        this.gcsNo = gcsNo;
    }

    public Integer getGcNo() {
        return gcNo;
    }

    public void setGcNo(Integer gcNo) {
        this.gcNo = gcNo;
    }

    public Integer getTrainerNo() {
        return trainerNo;
    }

    public void setTrainerNo(Integer trainerNo) {
        this.trainerNo = trainerNo;
    }


    public Date getGcsStart() {
        return gcsStart;
    }

    public void setGcsStart(Date gscStart) {
        this.gcsStart = gscStart;
    }

    public Date getGcsEnd() {
        return gcsEnd;
    }

    public void setGcsEnd(Date gscEnd) {
        this.gcsEnd = gscEnd;
    }

    public Integer getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(Integer minLimit) {
        this.minLimit = minLimit;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getGcsPrice() {
        return gcsPrice;
    }

    public void setGcsPrice(Integer gcsPrice) {
        this.gcsPrice = gcsPrice;
    }

    public Integer getGcsStatus() {
        return gcsStatus;
    }

    public void setGcsStatus(Integer gcsStatus) {
        this.gcsStatus = gcsStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCourseScheduleVO that = (GroupCourseScheduleVO) o;
        return Objects.equals(gcsNo, that.gcsNo) && Objects.equals(gcNo, that.gcNo) && Objects.equals(trainerNo, that.trainerNo) && Objects.equals(gcsStart, that.gcsStart) && Objects.equals(gcsEnd, that.gcsEnd) && Objects.equals(minLimit, that.minLimit) && Objects.equals(maxLimit, that.maxLimit) && Objects.equals(count, that.count) && Objects.equals(gcsPrice, that.gcsPrice) && Objects.equals(gcsStatus, that.gcsStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcsNo, gcNo, trainerNo, gcsStart, gcsEnd, minLimit, maxLimit, count, gcsPrice, gcsStatus);
    }

    @Override
    public String toString() {
        return "GroupCourseScheduleVO{" +
                "gcsNo=" + gcsNo +
                ", gcNo=" + gcNo +
                ", trainerNo=" + trainerNo +
                ", gscStart=" + gcsStart +
                ", gscEnd=" + gcsEnd +
                ", minLimit=" + minLimit +
                ", maxLimit=" + maxLimit +
                ", count=" + count +
                ", gcsPrice=" + gcsPrice +
                ", gcsStatus=" + gcsStatus +
                '}';
    }
}
