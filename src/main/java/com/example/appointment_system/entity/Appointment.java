package com.example.appointment_system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_info")
public class Appointment {

	// 自動產生之id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_id")
	private int autoId;

	// 病患id
	@Column(name = "patient_id")
	private String patientId;

	// 醫院id
	@Column(name = "hospital_name")
	private String hospitalName;

	// 醫生id
	@Column(name = "doctor_id")
	private String doctorId;

	// 預約看診日期
	@Column(name = "appointment_date")
	private LocalDate appointmentDate;

	// 星期幾: 星期一 星期二 星期三
	@Column(name = "week")
	private String week;

	// 門診時段 :早上 中午 晚上
	@Column(name = "appointment_time")
	private String appointmentTime;

	// 建立預約表單時間
	@Column(name = "create_time")
	private LocalDateTime createTime;

	public Appointment() {

	}

	public Appointment(String patientId,String doctorId, String appointmentTime, String week, 
			LocalDate appointmentDate, LocalDateTime createTime) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.week = week;
		this.appointmentDate = appointmentDate;
		this.createTime = createTime;
	}

	// 12/14新增的建構方法
	public Appointment(String patientId, String doctorId, String appointmentTime, String week,
			LocalDateTime createTime) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.week = week;
		this.createTime = createTime;

	}
	
	// 12/16新增!
		public Appointment(String patientId, String doctorId, String hospitalName, String appointmentTime, String week,
				LocalDate appointmentDate, LocalDateTime createTime) {
			this.patientId = patientId;
			this.doctorId = doctorId;
			this.hospitalName = hospitalName;
			this.appointmentTime = appointmentTime;
			this.week = week;
			this.appointmentDate = appointmentDate;
			this.createTime = createTime;
		}


	// 12/14新增的建構方法
	public Appointment(String patientId, String doctorId, String appointmentTime, String week) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.week = week;

	}

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
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