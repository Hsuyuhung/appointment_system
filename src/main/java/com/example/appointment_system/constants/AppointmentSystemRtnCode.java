package com.example.appointment_system.constants;

public enum AppointmentSystemRtnCode {

	CREATE_SUCCESSFUL("200", "新增成功。"), 
	UPDATE_SUCCESSFUL("200", "修改成功。"),
	DELETE_SUCCESSFUL("200", "刪除成功。"),
	HOSPITAL_ID_EXSITED("400", "醫院代碼已存在。"), 
	HOSPITAL_ID_WRONG("400", "醫院代碼格式錯誤。"),
	HOSPITAL_ID_EMPTY("400", "醫院代碼不得為空。"), 
	HOSPITAL_NAME_EMPTY("400", "醫院名稱不得為空。"),
	HOSPITAL_PHONE_EMPTY("400", "醫院電話不得為空。"),
	HOSPITAL_PHONE_WRONG("400", "醫院電話格式錯誤。"),
	HOSPITAL_CITY_EMPTY("400", "醫院城市不得為空。"),
	HOSPITAL_DISTRICT_EMPTY("400", "醫院地區不得為空。"), 
	HOSPITAL_ADDRESS_EMPTY("400", "醫院地址不得為空。"),
	HOSPITAL_DEPARTMENT_EMPTY("400", "醫院診別不得為空。"),
	HOSPITAL_DEPARTMENT_WRONG("400", "無此醫院診別。");

	private String code;
	private String message;

	private AppointmentSystemRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
