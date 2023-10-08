package com.woof.administrator.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;

public class AdministratorVO implements Serializable {
	private Integer adminNo;
	private String adminName;
	private String adminGender;
	private byte[] adminPhoto;
	private String adminEmail;
	private String adminPassword;
	private String adminTel;
	private String adminAddress;
	private Date adminBd;
	private String emergencyContactName;
	private Integer emergencyContactel;
	private Date adminHd;
	private Date adminRd;
	private Integer adminStatus;

	public AdministratorVO() {
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminGender() {
		return adminGender;
	}

	public void setAdminGender(String adminGender) {
		this.adminGender = adminGender;
	}

	public byte[] getAdminPhoto() {
		return adminPhoto;
	}

	public void setAdminPhoto(byte[] adminPhoto) {
		this.adminPhoto = adminPhoto;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public Date getAdminBd() {
		return adminBd;
	}

	public void setAdminBd(Date adminBd) {
		this.adminBd = adminBd;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public Integer getEmergencyContactel() {
		return emergencyContactel;
	}

	public void setEmergencyContactel(Integer emergencyContactel) {
		this.emergencyContactel = emergencyContactel;
	}

	public Date getAdminHd() {
		return adminHd;
	}

	public void setAdminHd(Date adminHd) {
		this.adminHd = adminHd;
	}

	public Date getAdminRd() {
		return adminRd;
	}

	public void setAdminRd(Date adminRd) {
		this.adminRd = adminRd;
	}

	public Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(adminPhoto);
		result = prime * result + Objects.hash(adminAddress, adminBd, adminEmail, adminGender, adminHd, adminName,
				adminNo, adminPassword, adminRd, adminStatus, adminTel, emergencyContactName, emergencyContactel);
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
		AdministratorVO other = (AdministratorVO) obj;
		return Objects.equals(adminAddress, other.adminAddress) && Objects.equals(adminBd, other.adminBd)
				&& Objects.equals(adminEmail, other.adminEmail) && Objects.equals(adminGender, other.adminGender)
				&& Objects.equals(adminHd, other.adminHd) && Objects.equals(adminName, other.adminName)
				&& Objects.equals(adminNo, other.adminNo) && Objects.equals(adminPassword, other.adminPassword)
				&& Arrays.equals(adminPhoto, other.adminPhoto) && Objects.equals(adminRd, other.adminRd)
				&& Objects.equals(adminStatus, other.adminStatus) && Objects.equals(adminTel, other.adminTel)
				&& Objects.equals(emergencyContactName, other.emergencyContactName)
				&& Objects.equals(emergencyContactel, other.emergencyContactel);
	}

	@Override
	public String toString() {
		return "AdministratorVO [adminNo=" + adminNo + ", adminName=" + adminName + ", adminGender=" + adminGender
				+ ", adminPhoto=" + Arrays.toString(adminPhoto) + ", adminEmail=" + adminEmail + ", adminPassword="
				+ adminPassword + ", adminTel=" + adminTel + ", adminAddress=" + adminAddress + ", adminBd=" + adminBd
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactel=" + emergencyContactel
				+ ", adminHd=" + adminHd + ", adminRd=" + adminRd + ", adminStatus=" + adminStatus + ", getAdminNo()="
				+ getAdminNo() + ", getAdminName()=" + getAdminName() + ", getAdminGender()=" + getAdminGender()
				+ ", getAdminPhoto()=" + Arrays.toString(getAdminPhoto()) + ", getAdminEmail()=" + getAdminEmail()
				+ ", getAdminPassword()=" + getAdminPassword() + ", getAdminTel()=" + getAdminTel()
				+ ", getAdminAddress()=" + getAdminAddress() + ", getAdminBd()=" + getAdminBd()
				+ ", getEmergencyContactName()=" + getEmergencyContactName() + ", getEmergencyContactel()="
				+ getEmergencyContactel() + ", getAdminHd()=" + getAdminHd() + ", getAdminRd()=" + getAdminRd()
				+ ", getAdminStatus()=" + getAdminStatus() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

}