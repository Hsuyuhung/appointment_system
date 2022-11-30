package com.example.appointment_system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor_info")
public class Doctor {

	
	@Id
	@Column(name = "doctor_id")
	private String doctorId;

	@Column(name = "hospital_id")
	private String hospitalId;


	@Column(name = "appointment_time")
	private String appointmentTime;

	@Column(name = "register")
	private String register;
	
	public Doctor() {
		
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}


}
