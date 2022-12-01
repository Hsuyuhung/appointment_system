package com.example.appointment_system.vo;

import com.example.appointment_system.entity.Hospital;

public class AppointmentSystemRes {

	private String message;

	private Hospital hospital;

	public AppointmentSystemRes() {

	}
	
	public AppointmentSystemRes(String message) {
		this.message = message;
	}

	public AppointmentSystemRes(String message, Hospital hospital) {
		this.message = message;
		this.hospital = hospital;
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
}
