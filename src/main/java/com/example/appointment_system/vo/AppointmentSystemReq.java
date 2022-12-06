package com.example.appointment_system.vo;

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

	private String id;

	private String password;

	private String newPassword;

	private String name;

	private String birthday;

	private String gender;

	private String eMail;

	public AppointmentSystemReq() {

	}
	
		public AppointmentSystemReq(String hospitalId, String department, String hospitalName, String phone, String city, String district,
			String address) {
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
	
	public AppointmentSystemReq(String hospitalId, String doctorId, String doctorName, String doctorDepartment, String appointmentTime) {
		this.hospitalId = hospitalId;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorDepartment = doctorDepartment;
		this.appointmentTime = appointmentTime;
	}

	public AppointmentSystemReq(String id, String password, String name, String birthday, String gender, String eMail) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.eMail = eMail;
	}

	public AppointmentSystemReq(String id, String password, String name, String eMail) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.eMail = eMail;
	}

	public AppointmentSystemReq(String id, String password, String newPassword) {
		this.id = id;
		this.password = password;
		this.newPassword = newPassword;

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
