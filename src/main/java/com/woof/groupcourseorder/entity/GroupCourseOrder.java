package com.woof.groupcourseorder.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.member.entity.Member;


@Entity
@Table(name = "group_course_order")
public class GroupCourseOrder implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GCO_NO" , updatable = false)
    private Integer gcoNo;
	
	@ManyToOne
	@JoinColumn(name = "MEM_NO" , referencedColumnName = "MEM_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "GCS_NO" , referencedColumnName = "GCS_NO")
	private GroupCourseSchedule groupCourseSchedule;
	
	@Column(name = "GCO_DATE" , nullable = false , insertable = false , updatable = false)
    private Timestamp gcoDate;
	
	@Column(name = "GCO_PAYMENT_METHOD" , nullable = false , columnDefinition = "TINYINT")
    private Integer gcoPaymentMethod;
	
	@Column(name = "GCO_SMMP" , nullable = false)
    private Integer gcoSmmp;
	
	@Column(name = "ACTUAL_AMOUNT")
    private Integer actualAmount;
	
	@Column(name = "GCO_STATUS" , nullable = false , columnDefinition = "TINYINT")
    private Integer gcoStatus;

    public GroupCourseOrder() {
    }

    public Integer getGcoNo() {
        return gcoNo;
    }

    public void setGcoNo(Integer gcoNo) {
        this.gcoNo = gcoNo;
    }



    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public GroupCourseSchedule getGroupCourseSchedule() {
		return groupCourseSchedule;
	}

	public void setGroupCourseSchedule(GroupCourseSchedule groupCourseSchedule) {
		this.groupCourseSchedule = groupCourseSchedule;
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
	public int hashCode() {
		return Objects.hash(gcoNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupCourseOrder other = (GroupCourseOrder) obj;
		return Objects.equals(gcoNo, other.gcoNo);
	}

	@Override
	public String toString() {
		return "GroupCourseOrder [gcoNo=" + gcoNo + ", gcoDate=" + gcoDate + ", gcoPaymentMethod=" + gcoPaymentMethod
				+ ", gcoSmmp=" + gcoSmmp + ", actualAmount=" + actualAmount + ", gcoStatus=" + gcoStatus + "]";
	}




    



}
