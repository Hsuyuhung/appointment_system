package com.example.appointment_system.vo;

import java.util.List;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppointmentSystemRes {

	private String message;

	@JsonProperty(value = "hospital_info")
	private Hospital hospital;

	@JsonProperty(value = "hospital_list")
	private List<Hospital> hospitalList;

	@JsonProperty(value = "doctor_info")
	private Doctor doctor;

	public AppointmentSystemRes() {

	}

	public AppointmentSystemRes(String message) {
		this.message = message;
	}

	public AppointmentSystemRes(String message, Hospital hospital) {
		this.message = message;
		this.hospital = hospital;
	}

	public AppointmentSystemRes(String message, List<Hospital> hospitalList) {
		this.message = message;
		this.hospitalList = hospitalList;
	}

	public AppointmentSystemRes(Doctor doctor, String message) {
		this.doctor = doctor;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Hospital> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
