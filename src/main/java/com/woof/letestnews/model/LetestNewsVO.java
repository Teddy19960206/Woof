package com.woof.letestnews.model;

import java.util.Arrays;
import java.util.Objects;

public class LetestNewsVO implements java.io.Serializable{
private Integer lnNo;
private String  lnTitle;
private String  lnContent;
private byte[]  lnPhoto;
private java.sql.Timestamp lnTime;
public LetestNewsVO() {
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
	LetestNewsVO other = (LetestNewsVO) obj;
	return Objects.equals(lnContent, other.lnContent) && Objects.equals(lnNo, other.lnNo)
			&& Arrays.equals(lnPhoto, other.lnPhoto) && Objects.equals(lnTime, other.lnTime)
			&& Objects.equals(lnTitle, other.lnTitle);
}
@Override
public String toString() {
	return "LetestNewsVO [lnNo=" + lnNo + ", lnTitle=" + lnTitle + ", lnContent=" + lnContent + ", lnPhoto="
			+ Arrays.toString(lnPhoto) + ", lnTime=" + lnTime + ", getLnNo()=" + getLnNo() + ", getLnTitle()="
			+ getLnTitle() + ", getLnContent()=" + getLnContent() + ", getLnPhoto()=" + Arrays.toString(getLnPhoto())
			+ ", getLnTime()=" + getLnTime() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
			+ ", toString()=" + super.toString() + "]";
}
}
