package com.example.appointment_system.vo;

public class AppointmentSystemReq {

	private String hospitalId;

	private String hospitalName;

	private String phone;

	private String city;

	private String district;

	private String address;

	private String department;

	public AppointmentSystemReq() {

	}

	public AppointmentSystemReq(String hospitalId, String hospitalName, String phone, String city, String district,
			String address, String department) {
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.city = city;
		this.district = district;
		this.address = address;
		this.city = city;
		this.district = district;
		this.address = address;
		this.department = department;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
