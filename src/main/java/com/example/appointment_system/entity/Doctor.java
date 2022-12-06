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

	@Column(name = "doctor_name")
	private String doctorName;

	@Column(name = "doctor_department")
	private String doctorDepartment;

	@Column(name = "hospital_id")
	private String hospitalId;

	@Column(name = "appointment_time")
	private String appointmentTime;

	public Doctor() {

	}

	public Doctor(String hospitalId, String doctorId, String doctorName, String doctorDepartment,
			String appointmentTime) {
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorDepartment = doctorDepartment;
		this.appointmentTime = appointmentTime;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorDepartment() {
		return doctorDepartment;
	}

	public void setDoctorDepartment(String doctorDepartment) {
		this.doctorDepartment = doctorDepartment;
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
}
