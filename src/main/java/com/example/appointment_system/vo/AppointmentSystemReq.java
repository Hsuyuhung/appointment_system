package com.example.appointment_system.vo;

public class AppointmentSystemReq {

	private String id;

	private String password;

	private String newPassword;

	private String name;

	private String birthday;

	private String gender;

	private String eMail;

	public AppointmentSystemReq() {

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
