package com.example.appointment_system.vo;

import java.util.List;

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
}
