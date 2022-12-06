package com.example.appointment_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppointmentSystemReq {

	private String hospitalId;

	private String department;

	private String hospitalName;

	private String phone;

	private String city;

	private String district;

	private String address;

	@JsonProperty("doctorId")
	private String doctorId;

	@JsonProperty("doctorName")
	private String doctorName;

	@JsonProperty("doctorDepartment")
	private String doctorDepartment;

	@JsonProperty("appointmentTime")
	private String appointmentTime;

	private String week;

	private String patientId;

	@JsonProperty("pdw")
	private String password;

	@JsonProperty("newpdw")
	private String newPassword;

	private String patientName;

	private String birthday;

	private String gender;

	private String email;

	private String medical;

	private String createTime;
	
	private int chartNo;

	public AppointmentSystemReq() {

	}

	public AppointmentSystemReq(String patientId, String doctorId, String appointmentTime, String week, String medical,
			String createTime, String email, String birthday) {

		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentTime = appointmentTime;
		this.week = week;
		this.medical = medical;
		this.createTime = createTime;
		this.email = email;
		this.birthday =birthday;

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

	public String getMedical() {
		return medical;
	}

	public void setMedical(String medical) {
		this.medical = medical;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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