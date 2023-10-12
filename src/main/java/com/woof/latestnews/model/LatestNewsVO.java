package com.woof.latestnews.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="latest_news")
public class LatestNewsVO implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="LN_NO",updatable=false ,nullable=false)
	private Integer lnNo;
@Column(name="LN_TITLE" ,nullable=false)
	private String lnTitle;
@Column(name="LN_CONTENT",nullable=false)
	private String lnContent;
@Column(name="LN_PHOTO" ,columnDefinition="MEDIUMBLOB",nullable=false)
	private byte[] lnPhoto;
@Column(name="LN_TIME",nullable=false)
	private Timestamp lnTime;

	public LatestNewsVO() {
	}

	public Integer getLnNo() {
		return lnNo;
	}

	public void setLnNo(Integer lnNo) {
		this.lnNo = lnNo;
	}

	public String getLnTitle() {
		return lnTitle;
	}

	public void setLnTitle(String lnTitle) {
		this.lnTitle = lnTitle;
	}

	public String getLnContent() {
		return lnContent;
	}

	public void setLnContent(String lnContent) {
		this.lnContent = lnContent;
	}

	public byte[] getLnPhoto() {
		return lnPhoto;
	}

	public void setLnPhoto(byte[] lnPhoto) {
		this.lnPhoto = lnPhoto;
	}

	public java.sql.Timestamp getLnTime() {
		return lnTime;
	}

	public void setLnTime(java.sql.Timestamp lnTime) {
		this.lnTime = lnTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(lnPhoto);
		result = prime * result + Objects.hash(lnContent, lnNo, lnTime, lnTitle);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatestNewsVO other = (LatestNewsVO) obj;
		return Objects.equals(lnContent, other.lnContent) && Objects.equals(lnNo, other.lnNo)
				&& Arrays.equals(lnPhoto, other.lnPhoto) && Objects.equals(lnTime, other.lnTime)
				&& Objects.equals(lnTitle, other.lnTitle);
	}

	@Override
	public String toString() {
		return "LetestNewsVO [lnNo=" + lnNo + ", lnTitle=" + lnTitle + ", lnContent=" + lnContent + ", lnPhoto="
				+ Arrays.toString(lnPhoto) + ", lnTime=" + lnTime + ", getLnNo()=" + getLnNo() + ", getLnTitle()="
				+ getLnTitle() + ", getLnContent()=" + getLnContent() + ", getLnPhoto()="
				+ Arrays.toString(getLnPhoto()) + ", getLnTime()=" + getLnTime() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}
