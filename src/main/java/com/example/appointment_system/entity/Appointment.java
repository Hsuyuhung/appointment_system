package com.example.appointment_system.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@Column(name = "id")
	int id;
	
	@Column(name = "hospital_id")
	private String hospitalId;
	
	@Column(name = "doctor_id")
	private String doctorId;
	
	@Column(name = "appintment_time")
	private String appintmentTime;
	
	@Column(name = "register")
	private String register;
	
	
	public Appointment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	
}
