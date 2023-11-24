package com.woof.appointmentdetail.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;
import com.woof.commentreport.entity.CommentReport;
import com.woof.member.entity.Member;
import com.woof.trainer.entity.Trainer;

public class AppointmentDetailDTO {

	private Integer ptaNo;

	private String ptaComment;

	private Timestamp commentTime;

	private Timestamp commentUpTime;
	
	private Date appTime;

	public Integer getPtaNo() {
		return ptaNo;
	}

	public void setPtaNo(Integer ptaNo) {
		this.ptaNo = ptaNo;
	}

	public String getPtaComment() {
		return ptaComment;
	}

	public void setPtaComment(String ptaComment) {
		this.ptaComment = ptaComment;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public Timestamp getCommentUpTime() {
		return commentUpTime;
	}

	public void setCommentUpTime(Timestamp commentUpTime) {
		this.commentUpTime = commentUpTime;
	}

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appTime, commentTime, commentUpTime, ptaComment, ptaNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDetailDTO other = (AppointmentDetailDTO) obj;
		return Objects.equals(appTime, other.appTime) && Objects.equals(commentTime, other.commentTime)
				&& Objects.equals(commentUpTime, other.commentUpTime) && Objects.equals(ptaComment, other.ptaComment)
				&& Objects.equals(ptaNo, other.ptaNo);
	}

	@Override
	public String toString() {
		return "AppointmentDetailDTO [ptaNo=" + ptaNo + ", ptaComment=" + ptaComment + ", commentTime=" + commentTime
				+ ", commentUpTime=" + commentUpTime + ", appTime=" + appTime + "]";
	}

	
	
	
}
