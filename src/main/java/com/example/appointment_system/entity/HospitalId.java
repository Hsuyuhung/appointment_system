package com.example.appointment_system.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HospitalId implements Serializable {

	private String hospitalId;

	private String department;

	public HospitalId() {

	}

	public HospitalId(String hospitalId, String department) {
		this.hospitalId = hospitalId;
		this.department = department;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
