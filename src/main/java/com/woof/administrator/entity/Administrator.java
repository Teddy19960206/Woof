package com.woof.administrator.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.woof.util.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;
import com.woof.trainer.entity.Trainer;

@Entity
@Table(name = "administrator")
public class Administrator implements Serializable {
	@Expose
	@Id
	@Column(name = "ADMIN_NO", updatable = false, nullable = false)
	private String adminNo;
	@Expose
	@Column(name = "ADMIN_NAME", nullable = false)
	private String adminName;
	@Expose
	@Column(name = "ADMIN_GENDER", nullable = false, columnDefinition = "CHAR")
	private String adminGender;

	@JsonIgnore
	@Expose
	@Column(name = "ADMIN_PHOTO", columnDefinition = "MEDIUMBLOB")
	private byte[] adminPhoto;
	@Expose
	@Column(name = "ADMIN_EMAIL", nullable = false)
	private String adminEmail;
	@Expose
	@Column(name = "ADMIN_PASSWORD" ,nullable = false)
	private String adminPassword;
	@Expose
	@Column(name = "ADMIN_TEL", nullable = false)
	private String adminTel;
	@Expose
	@Column(name = "ADMIN_ADDRESS", nullable = false)
	private String adminAddress;
	@Expose
	@Column(name = "ADMIN_BD")
	private Date adminBd;
	@Expose
	@Column(name = "EMERGENCY_CONTACTNAME", nullable = false)
	private String emergencyContactName;
	@Expose
	@Column(name = "EMERGENCY_CONTACTEL", nullable = false)
	private String emergencyContactel;
	@Expose
	@Column(name = "ADMIN_HD", nullable = false)
	private Date adminHd;
	@Expose
	@Column(name = "ADMIN_RD")
	private Date adminRd;
	@Expose
	@Column(name = "ADMIN_STATUS", nullable = false, columnDefinition = "TINYINT")
	private Integer adminStatus;
	@Expose
	@Column(name = "ADMIN_VERIFY_STATUS", columnDefinition = "TINYINT",nullable = false)
	private Integer adminVerifyStatus;
	@Expose
	@Column(name = "ADMIN_FUNC_NAME", columnDefinition = "TINYINT",nullable = false)
	private Integer adminFuncName;
	public Integer getAdminVerifyStatus() {
		return adminVerifyStatus;
	}
	
	@Transient
	private String imgStr ;

	public String getImgStr() {
		return imgStr;
	}
	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	public void setAdminVerifyStatus(Integer adminVerifyStatus) {
		this.adminVerifyStatus = adminVerifyStatus;
	}

	public Integer getAdminFuncName() {
		return adminFuncName;
	}

	public void setAdminFuncName(Integer adminFuncName) {
		this.adminFuncName = adminFuncName;
	}

	
	
	

	

	public Administrator() {
		super();
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
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

	public String getEmergencyContactel() {
		return emergencyContactel;
	}

	public void setEmergencyContactel(String emergencyContactel) {
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
		result = prime * result + Objects.hash(adminAddress, adminBd, adminEmail, adminFuncName, adminGender, adminHd,
				adminName, adminNo, adminPassword, adminRd, adminStatus, adminTel, adminVerifyStatus,
				emergencyContactName, emergencyContactel, imgStr);
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
		Administrator other = (Administrator) obj;
		return Objects.equals(adminAddress, other.adminAddress) && Objects.equals(adminBd, other.adminBd)
				&& Objects.equals(adminEmail, other.adminEmail) && Objects.equals(adminFuncName, other.adminFuncName)
				&& Objects.equals(adminGender, other.adminGender) && Objects.equals(adminHd, other.adminHd)
				&& Objects.equals(adminName, other.adminName) && Objects.equals(adminNo, other.adminNo)
				&& Objects.equals(adminPassword, other.adminPassword) && Arrays.equals(adminPhoto, other.adminPhoto)
				&& Objects.equals(adminRd, other.adminRd) && Objects.equals(adminStatus, other.adminStatus)
				&& Objects.equals(adminTel, other.adminTel)
				&& Objects.equals(adminVerifyStatus, other.adminVerifyStatus)
				&& Objects.equals(emergencyContactName, other.emergencyContactName)
				&& Objects.equals(emergencyContactel, other.emergencyContactel) && Objects.equals(imgStr, other.imgStr);
	}

	@Override
	public String toString() {
		return "Administrator [adminNo=" + adminNo + ", adminName=" + adminName + ", adminGender=" + adminGender
				+ ", adminPhoto=" + Arrays.toString(adminPhoto) + ", adminEmail=" + adminEmail + ", adminPassword="
				+ adminPassword + ", adminTel=" + adminTel + ", adminAddress=" + adminAddress + ", adminBd=" + adminBd
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactel=" + emergencyContactel
				+ ", adminHd=" + adminHd + ", adminRd=" + adminRd + ", adminStatus=" + adminStatus
				+ ", adminVerifyStatus=" + adminVerifyStatus + ", adminFuncName=" + adminFuncName + ", imgStr=" + imgStr
				+ "]";
	}



}