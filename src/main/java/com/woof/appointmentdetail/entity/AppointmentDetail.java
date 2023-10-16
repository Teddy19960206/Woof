package com.woof.appointmentdetail.entity;

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

import com.google.gson.annotations.Expose;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;


@Entity
@Table(name="appointment_detail")
public class AppointmentDetail {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AD_NO" , updatable = false , nullable = false)
	private Integer adNo;
	
	@ManyToOne
	@JoinColumn(name="PTA_NO" , referencedColumnName = "PTA_NO")
	private PrivateTrainingAppointmentForm privateTrainingAppointmentForm;
	
	@Expose
	@Column(name="APP_TIME" , nullable = false)
	private Timestamp appTime;
	
	@Expose
	@Column(name="APP_STATUS" , nullable = false ,  columnDefinition = "TINYINT")
	private Integer appStatus;
	
	@Expose
	@Column(name="APP_VENUE" , nullable = false)
	private String appVenue;
	
	public AppointmentDetail() {
		super();
	}

	public Integer getAdNo() {
		return adNo;
	}
	public void setAdNo(Integer adNo) {
		this.adNo = adNo;
	}
	
	public PrivateTrainingAppointmentForm getPrivateTrainingAppointmentForm() {
		return privateTrainingAppointmentForm;
	}

	public void setPrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		this.privateTrainingAppointmentForm = privateTrainingAppointmentForm;
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
		return Objects.hash(adNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDetail other = (AppointmentDetail) obj;
		return Objects.equals(adNo, other.adNo);
	}
	@Override
	public String toString() {
		return "AppointmentDetail [adNo=" + adNo + ", ptaNo=" + (privateTrainingAppointmentForm != null ? privateTrainingAppointmentForm.getPtaNo() : "N/A") + ", appTime=" + appTime + ", appStatus="
				+ appStatus + ", appVenue=" + appVenue + "]";
	}

}