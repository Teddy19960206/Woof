package com.woof.groupcourseorder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "group_course_order")
public class GroupCourseOrderVO implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCO_NO" , updatable = false)
    private Integer gcoNo;
	
	@Column(name = "MEM_NO")
	private	Integer memNo;
	
	@Column(name = "GCS_NO")
    private Integer gcsNo;
	
	@Column(name = "GCO_DATE")
    private Timestamp gcoDate;
	
	@Column(name = "GCO_PAYMENT_METHOD")
    private Integer gcoPaymentMethod;
	
	@Column(name = "GCO_SMMP")
    private Integer gcoSmmp;
	
	@Column(name = "ACTUAL_AMOUNT")
    private Integer actualAmount;
	
	@Column(name = "GCO_STATUS")
    private Integer gcoStatus;

    public GroupCourseOrderVO() {
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
        GroupCourseOrderVO that = (GroupCourseOrderVO) o;
        return Objects.equals(gcoNo, that.gcoNo) && Objects.equals(memNo, that.memNo) && Objects.equals(gcsNo, that.gcsNo) && Objects.equals(gcoDate, that.gcoDate) && Objects.equals(gcoPaymentMethod, that.gcoPaymentMethod) && Objects.equals(gcoSmmp, that.gcoSmmp) && Objects.equals(actualAmount, that.actualAmount) && Objects.equals(gcoStatus, that.gcoStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcoNo, memNo, gcsNo, gcoDate, gcoPaymentMethod, gcoSmmp, actualAmount, gcoStatus);
    }

    @Override
    public String toString() {
        return "GroupCourseOrderVO{" +
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
