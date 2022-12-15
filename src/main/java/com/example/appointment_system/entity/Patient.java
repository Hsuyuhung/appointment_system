package com.example.appointment_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "patient_info")
public class Patient {

	
    //病人ID
	@Id
	@Column(name = "patient_id")
	private String patientId;

	//密碼
	@Column(name = "password")
	private String password;

	//命人姓名
	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	public Patient() {

	}

	public Patient(String patientId, String password) {
		this.patientId = patientId;
		this.password = password;
	}

	public Patient(String patientId, String password, String patientName, String birthday, String gender, String email) {
		this.patientId = patientId;
		this.password = password;
		this.patientName = patientName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;

	}

	public Patient(String patientId, String password, String patientName, String email) {
		this.patientId = patientId;
		this.password = password;
		this.patientName = patientName;
		this.email = email;

	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}