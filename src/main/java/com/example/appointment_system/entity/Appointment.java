package com.example.appointment_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_info")
public class Appointment {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "hospital_id")
	private String hospitalId;

	@Column(name = "doctor_id")
	private String doctorId;

	@Column(name = "appintment_time")
	private String appintmentTime;

	@Column(name = "regiter")
	private String regiter;

	public Appointment() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getAppintmentTime() {
		return appintmentTime;
	}

	public void setAppintmentTime(String appintmentTime) {
		this.appintmentTime = appintmentTime;
	}

	public String getRegiter() {
		return regiter;
	}

	public void setRegiter(String regiter) {
		this.regiter = regiter;
	}

}
