package com.woof.groupcourseorder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class GroupCourseOrder implements Serializable {

    private Integer gcoNo;
    private Integer memNo;
    private Integer gcsNo;
    private Timestamp gcoDate;
    private Integer gcoPaymentMethod;
    private Integer gcoSmmp;
    private Integer actualAmount;
    private Integer gcoStatus;

    public GroupCourseOrder() {
    }

    public Integer getGcoNo() {
        return gcoNo;
    }

    public void setGcoNo(Integer gcoNo) {
        this.gcoNo = gcoNo;
    }

    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public Integer getGcsNo() {
        return gcsNo;
    }

    public void setGcsNo(Integer gcsNo) {
        this.gcsNo = gcsNo;
    }

    public Timestamp getGcoDate() {
        return gcoDate;
    }

    public void setGcoDate(Timestamp gcoDate) {
        this.gcoDate = gcoDate;
    }

    public Integer getGcoPaymentMethod() {
        return gcoPaymentMethod;
    }

    public void setGcoPaymentMethod(Integer gcoPaymentMethod) {
        this.gcoPaymentMethod = gcoPaymentMethod;
    }

    public Integer getGcoSmmp() {
        return gcoSmmp;
    }

    public void setGcoSmmp(Integer gcoSmmp) {
        this.gcoSmmp = gcoSmmp;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getGcoStatus() {
        return gcoStatus;
    }

    public void setGcoStatus(Integer gcoStatus) {
        this.gcoStatus = gcoStatus;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCourseOrder that = (GroupCourseOrder) o;
        return Objects.equals(gcoNo, that.gcoNo) && Objects.equals(memNo, that.memNo) && Objects.equals(gcsNo, that.gcsNo) && Objects.equals(gcoDate, that.gcoDate) && Objects.equals(gcoPaymentMethod, that.gcoPaymentMethod) && Objects.equals(gcoSmmp, that.gcoSmmp) && Objects.equals(actualAmount, that.actualAmount) && Objects.equals(gcoStatus, that.gcoStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcoNo, memNo, gcsNo, gcoDate, gcoPaymentMethod, gcoSmmp, actualAmount, gcoStatus);
    }

    @Override
    public String toString() {
        return "GroupCourseOrder{" +
                "gcoNo=" + gcoNo +
                ", memNo=" + memNo +
                ", gcsNo=" + gcsNo +
                ", gcoDate=" + gcoDate +
                ", gcoPaymentMethod=" + gcoPaymentMethod +
                ", gcoSmmp=" + gcoSmmp +
                ", actualAmount=" + actualAmount +
                ", gcoStatus=" + gcoStatus +
                '}';
    }



}
