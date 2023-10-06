package com.woof.groupcourseschedule.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GroupCourseSchedule implements Serializable {
    private Integer gcsNo;
    private Integer gcNo;
    private Integer trainerNo;
    private Integer gcoNo;
    private Date gscStart;
    private Date gscEnd;
    private Integer minLimit;
    private Integer maxLimit;
    private Integer count;
    private Integer gcsPrice;
    private Integer gcsStatus;

    public GroupCourseSchedule() {
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

    public Integer getGcoNo() {
        return gcoNo;
    }

    public void setGcoNo(Integer gcoNo) {
        this.gcoNo = gcoNo;
    }

    public Date getGscStart() {
        return gscStart;
    }

    public void setGscStart(Date gscStart) {
        this.gscStart = gscStart;
    }

    public Date getGscEnd() {
        return gscEnd;
    }

    public void setGscEnd(Date gscEnd) {
        this.gscEnd = gscEnd;
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
        GroupCourseSchedule that = (GroupCourseSchedule) o;
        return Objects.equals(gcsNo, that.gcsNo) && Objects.equals(gcNo, that.gcNo) && Objects.equals(trainerNo, that.trainerNo) && Objects.equals(gcoNo, that.gcoNo) && Objects.equals(gscStart, that.gscStart) && Objects.equals(gscEnd, that.gscEnd) && Objects.equals(minLimit, that.minLimit) && Objects.equals(maxLimit, that.maxLimit) && Objects.equals(count, that.count) && Objects.equals(gcsPrice, that.gcsPrice) && Objects.equals(gcsStatus, that.gcsStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcsNo, gcNo, trainerNo, gcoNo, gscStart, gscEnd, minLimit, maxLimit, count, gcsPrice, gcsStatus);
    }

    @Override
    public String toString() {
        return "GroupCourseSchedule{" +
                "gcsNo=" + gcsNo +
                ", gcNo=" + gcNo +
                ", trainerNo=" + trainerNo +
                ", gcoNo=" + gcoNo +
                ", gscStart=" + gscStart +
                ", gscEnd=" + gscEnd +
                ", minLimit=" + minLimit +
                ", maxLimit=" + maxLimit +
                ", count=" + count +
                ", gcsPrice=" + gcsPrice +
                ", gcsStatus=" + gcsStatus +
                '}';
    }
}
