package com.woof.appointmentdetail.model;

import java.sql.Timestamp;
import java.util.Objects;

public class AppointmentDetailVO {
	
	private Integer adNo;
	private Integer ptaNo;
	private Timestamp appTime;
	private Integer appStatus;
	private String appVenue;
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
