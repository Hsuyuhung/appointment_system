package com.example.appointment_system.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.appointment_system.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppoinmentInfo {
	
	private int chartNo;
	private String hospitalName;
	private Doctor doctor;
	private LocalDate appoinmentDate;
	private String week;
	private String appointmentTime;
	private LocalDateTime createTime;

	public AppoinmentInfo() {

	}
	
	public AppoinmentInfo(int chartNo, String hospitalName, Doctor doctor, LocalDate appoinmentDate, String week, String appointmentTime, LocalDateTime createTime) {
		this.chartNo = chartNo;
		this.hospitalName = hospitalName;
		this.doctor = doctor;
		this.appoinmentDate = appoinmentDate;
		this.week = week;
		this.appointmentTime = appointmentTime;
		this.createTime = createTime;
	}

	public int getChartNo() {
		return chartNo;
	}

	public void setChartNo(int chartNo) {
		this.chartNo = chartNo;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getAppoinmentDate() {
		return appoinmentDate;
	}

	public void setAppoinmentDate(LocalDate appoinmentDate) {
		this.appoinmentDate = appoinmentDate;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}