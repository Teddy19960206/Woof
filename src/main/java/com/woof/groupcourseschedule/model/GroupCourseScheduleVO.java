package com.woof.groupcourseschedule.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GroupCourseScheduleVO implements Serializable {
    private Integer gcsNo;
    private Integer gcNo;
    private Integer trainerNo;
    private Date gcsStart;
    private Date gcsEnd;
    private Integer minLimit;
    private Integer maxLimit;
    private Integer count;
    private Integer gcsPrice;
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
