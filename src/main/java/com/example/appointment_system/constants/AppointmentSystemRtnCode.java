package com.example.appointment_system.constants;

public enum AppointmentSystemRtnCode {

	CREATE_SUCCESSFUL("200", "新增成功。"), 
	UPDATE_SUCCESSFUL("200", "修改成功。"),
	DELETE_SUCCESSFUL("200", "刪除成功。"),
	SEARCH_SUCCESSFUL("200", "搜尋成功。"),
	USER_LOGIN_SUCCESSFUL("200", "歡迎登入。"),
	ADMINISTRATOR_LOGIN_SUCCESSFUL("200", "管理者登入成功。"),
	ACCOUNT_OR_PASSWORD_WRONG("400", "帳號或密碼錯誤。"),
	NOT_FIND("400", "查無結果。"),
	HOSPITAL_ID_EXSITED("400", "已存在相同醫院代碼與科別。"), 
	HOSPITAL_ID_WRONG("400", "醫院代碼格式錯誤。"),
	HOSPITAL_ID_NONEXIST("400", "查無此醫院代碼。"),
	HOSPITAL_ID_EMPTY("400", "醫院代碼不得為空。"), 
	HOSPITAL_NAME_EMPTY("400", "醫院名稱不得為空。"),
	HOSPITAL_PHONE_EMPTY("400", "醫院電話不得為空。"),
	HOSPITAL_PHONE_WRONG("400", "醫院電話格式錯誤。"),
	HOSPITAL_CITY_EMPTY("400", "醫院城市不得為空。"),
	HOSPITAL_DISTRICT_EMPTY("400", "醫院地區不得為空。"), 
	HOSPITAL_ADDRESS_EMPTY("400", "醫院地址不得為空。"),
	HOSPITAL_DEPARTMENT_EMPTY("400", "醫院診別不得為空。"),
	HOSPITAL_DEPARTMENT_WRONG("400", "無此醫院診別。"),
	HOSPITAL_UPDATE_EMPTY("400", "請輸入欲修改之資料。"),
	DOCTORID_REQUIRED("400", "醫生ID不能為空。"), 
	DOCTORNAME_REQUIRED("400", "醫生姓名不能為空。"),
	DOCTORDEPARTMENT_REQUIRED("400", "醫生所屬科別不能為空。"), 
	HOSPITALID_REQUIRED("400", "醫院代碼不能為空。"),
	DEPARTMENT_REQUIRED("400", "醫院科別不能為空。"),
	DOCTORAPPOINTMENTTIME_REQUIRED("400", "醫生工作時間不能為空。"),
	DOCTORID_EXISTED("400", "醫生ID已經存在。"),
	DOCTORAPPOINTMENTTIME_WEEK_EXISTED("400", "同醫生工作時間及星期已經存在。"),
	DOCTORID_INEXISTED("400", "醫生ID不存在。"),
	DOCTOR_ID_EXISTED("400", "醫生ID格式錯誤。"),
	DOCTORNAME_DOCTORDEPARTMENT_HOSPITALID_REQUIRED("400", "醫生姓名、"
			+ "科別、醫院代碼，其中一項不能為空。"),
	HOSPITALID_DEPARTMENT_INEXISTED("400", "醫院代碼或醫院科別不存在。"),
	WEEK_REQUIRED("400", "星期不可為空。"),
	SUCCESSFUL("200", "帳號建立成功。"), 
	ID_NULL("400", "請輸入帳號。"), 
	ID_FAIL("400", "帳號格式錯誤。"),
	ID_EXIST("400", "此帳號已存在，無法建立。"), 
	ID_EMPTY("400", "查無此帳號。"), 
	PASSWORD_NULL("400", "請輸入密碼。"),
	PASSWORD_FAIL("400", "PASSWORD格式錯誤"), 
	PASSWORD_ERROR("400", "PASSWORD輸入錯誤"), 
	PASSWORD_CONFIRM("400", "密碼與重複輸入密碼不相符。"), 
	PASSWORD_UNREPEAT("400", "新密碼不得與舊密碼相同。"), 
	NAME_NULL("400", "NAME輸入為NULL"),
	NAME_FAIL("400", "NAME格式錯誤"), 
	NAME_EXIST("400", "NAME is empty!!"), 
	BIRTHDAY_NULL("400", "BIRTHDAY輸入為NULL"), 
	BIRTHDAY_FAIL("400", "BIRTHDAY is fail!!"), 
	GENDER_NULL("400", "請輸入性別。"), 
	GENDER_FAIL("400", "GENDER格式錯誤"), 
	EMAIL_NULL("400", "EMAIL輸入為NULL"), 
	CREATE_APPOINMENT_NULL("400","額滿，建立預約失敗"), 
	EMAIL_FAIL("400", "EMAIL格式錯誤"),
	APPOINMENT_CHART_EMPTY("400", "病歷號不得為空。"),
	APPOINMENT_CHART_WRONG("400", "查無此病歷號。"),
	CHECK_WEEK("400", "星期輸入錯誤"),
	CREATE_DOUBLE_APPOINMENT_NULL("400","已經預約了");

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