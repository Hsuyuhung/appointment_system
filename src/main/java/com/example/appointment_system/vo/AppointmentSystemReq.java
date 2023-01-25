package com.example.appointment_system.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppointmentSystemReq {

	private String hospitalId;

	private String department;

	private String hospitalName;

	private String phone;

	private String city;

	private String district;

	private String address;

	private String doctorId;

	private String doctorName;

	private String doctorDepartment;

	private String appointmentTime;

	private String week;

	private String newWeek;

	private String newAppointmentTime;

	private String patientId;

	private String password;

	private String confirmPassword;

	private String newPassword;

	private String patientName;

	private String birthday;

	private String gender;

	private String email;

	private LocalDateTime createTime;

	private int chartNo;

	// 預約看診日期 12/14新增
	private LocalDate appointmentDate;

	public AppointmentSystemReq() {

	}

	public AppointmentSystemReq(String patientId, String doctorId, String appointmentTime, String week,
			LocalDate appointmentDate, LocalDateTime createTime) {

		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.week = week;
		this.appointmentDate = appointmentDate;
		this.createTime = createTime;

	}

	public AppointmentSystemReq(String hospitalId, String department, String hospitalName, String phone, String city,
			String district, String address) {
		this.hospitalId = hospitalId;
		this.department = department;
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.city = city;
		this.district = district;
		this.address = address;
		this.city = city;
		this.district = district;
		this.address = address;
	}

	public void createDoctor(String hospitalId, String doctorId, String doctorName, String doctorDepartment,
			String appointmentTime, String week) {
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorDepartment = doctorDepartment;
		this.appointmentTime = appointmentTime;
		this.week = week;
	}

	public AppointmentSystemReq(String patientId, String password, String patientName, String birthday, String gender,
			String email) {
		this.patientId = patientId;
		this.password = password;
		this.patientName = patientName;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
	}

	public AppointmentSystemReq(String patientId, String password, String patientName, String email) {
		this.patientId = patientId;
		this.password = password;
		this.patientName = patientName;
		this.email = email;
	}

	public AppointmentSystemReq(String patientId, String password, String newPassword) {
		this.patientId = patientId;
		this.password = password;
		this.newPassword = newPassword;

	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
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

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getNewWeek() {
		return newWeek;
	}

	public void setNewWeek(String newWeek) {
		this.newWeek = newWeek;
	}

	public String getNewAppointmentTime() {
		return newAppointmentTime;
	}

	public void setNewAppointmentTime(String newAppointmentTime) {
		this.newAppointmentTime = newAppointmentTime;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public int getChartNo() {
		return chartNo;
	}

	public void setChartNo(int chartNo) {
		this.chartNo = chartNo;
	}
}