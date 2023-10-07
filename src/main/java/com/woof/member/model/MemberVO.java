package com.woof.member.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class MemberVO implements java.io.Serializable {
	private Integer memNo;
	private String memName;
	private String memGender;
	private byte[] memPhoto;
	private String memEmail;
	private String memPassword;
	private String memTel;
	private String memAdress;
	private Date memBd;
	private Integer momoPoint;
	private Integer totalClass;
	private Integer memStatus;

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public byte[] getMemPhoto() {
		return memPhoto;
	}

	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemAdress() {
		return memAdress;
	}

	public void setMemAdress(String memAdress) {
		this.memAdress = memAdress;
	}

	public Date getMemBd() {
		return memBd;
	}

	public void setMemBd(Date memBd) {
		this.memBd = memBd;
	}

	public Integer getMomoPoint() {
		return momoPoint;
	}

	public void setMomoPoint(Integer momoPoint) {
		this.momoPoint = momoPoint;
	}

	public Integer getTotalClass() {
		return totalClass;
	}

	public void setTotalClass(Integer totalClass) {
		this.totalClass = totalClass;
	}

	public Integer getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(memPhoto);
		result = prime * result + Objects.hash(memAdress, memBd, memEmail, memGender, memName, memNo, memPassword,
				memStatus, memTel, momoPoint, totalClass);
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
		MemberVO other = (MemberVO) obj;
		return Objects.equals(memAdress, other.memAdress) && Objects.equals(memBd, other.memBd)
				&& Objects.equals(memEmail, other.memEmail) && Objects.equals(memGender, other.memGender)
				&& Objects.equals(memName, other.memName) && Objects.equals(memNo, other.memNo)
				&& Objects.equals(memPassword, other.memPassword) && Arrays.equals(memPhoto, other.memPhoto)
				&& Objects.equals(memStatus, other.memStatus) && Objects.equals(memTel, other.memTel)
				&& Objects.equals(momoPoint, other.momoPoint) && Objects.equals(totalClass, other.totalClass);
	}

	@Override
	public String toString() {
		return "MemberVO [memNo=" + memNo + ", memName=" + memName + ", memGender=" + memGender + ", memPhoto="
				+ Arrays.toString(memPhoto) + ", memEmail=" + memEmail + ", memPassword=" + memPassword + ", memTel="
				+ memTel + ", memAdress=" + memAdress + ", memBd=" + memBd + ", momoPoint=" + momoPoint
				+ ", totalClass=" + totalClass + ", memStatus=" + memStatus + "]";
	}
}