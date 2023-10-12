package com.woof.appointmentdetail.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="appointment_detail")
public class AppointmentDetailVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AD_NO", updatable = false)
	private Integer adNo;
	
	@Column(name="PTA_NO")
	private Integer ptaNo;
	
	@Column(name="APP_TIME")
	private Timestamp appTime;
	
	@Column(name="APP_STATUS")
	private Integer appStatus;
	
	@Column(name="APP_VENUE")
	private String appVenue;
	
	public AppointmentDetailVO() {
		super();
	}
	
	public AppointmentDetailVO(Integer adNo, Integer ptaNo, Timestamp appTime, Integer appStatus, String appVenue) {
		super();
		this.adNo = adNo;
		this.ptaNo = ptaNo;
		this.appTime = appTime;
		this.appStatus = appStatus;
		this.appVenue = appVenue;
	}

	public Integer getAdNo() {
		return adNo;
	}
	public void setAdNo(Integer adNo) {
		this.adNo = adNo;
	}
	public Integer getPtaNo() {
		return ptaNo;
	}
	public void setPtaNo(Integer ptaNo) {
		this.ptaNo = ptaNo;
	}
	public Timestamp getAppTime() {
		return appTime;
	}
	public void setAppTime(Timestamp appTime) {
		this.appTime = appTime;
	}
	public Integer getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}
	public String getAppVenue() {
		return appVenue;
	}
	public void setAppVenue(String appVenue) {
		this.appVenue = appVenue;
	}
	@Override
	public int hashCode() {
		return Objects.hash(adNo, appStatus, appTime, appVenue, ptaNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDetailVO other = (AppointmentDetailVO) obj;
		return Objects.equals(adNo, other.adNo) && Objects.equals(appStatus, other.appStatus)
				&& Objects.equals(appTime, other.appTime) && Objects.equals(appVenue, other.appVenue)
				&& Objects.equals(ptaNo, other.ptaNo);
	}
	@Override
	public String toString() {
		return "AppointmentDetail [adNo=" + adNo + ", ptaNo=" + ptaNo + ", appTime=" + appTime + ", appStatus="
				+ appStatus + ", appVenue=" + appVenue + "]";
	}

}