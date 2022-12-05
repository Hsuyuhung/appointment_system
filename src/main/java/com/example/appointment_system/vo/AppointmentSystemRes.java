package com.example.appointment_system.vo;

import java.util.List;

import com.example.appointment_system.entity.Patient;

public class AppointmentSystemRes {

	private Patient patient;

	private String message;

	private List<Patient> patientlist;

	public AppointmentSystemRes() {

	}

	public AppointmentSystemRes(Patient patient, String message) {
		this.patient = patient;
		this.message = message;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Patient> getPatientlist() {
		return patientlist;
	}

	public void setPatientlist(List<Patient> patientlist) {
		this.patientlist = patientlist;
	}

	
}