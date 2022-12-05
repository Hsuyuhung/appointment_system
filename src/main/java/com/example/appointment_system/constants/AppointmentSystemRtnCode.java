package com.example.appointment_system.constants;

public enum AppointmentSystemRtnCode {

	SUCCESSFUL("200", "Success"), 
	UPDATE_SUCCESSFUL("200", "Update Success "), 
	DELETE_SUCCESSFUL("200", "Delete Success "), 
	ID_NULL("400", "ID is null!!"), 
	ID_FAIL("400", "ID is fail!!"),
	ID_EXIST("400", "ID is exist!!"), 
	ID_EMPTY("400", "ID is empty!!"), 
	PASSWORD_NULL("400", "PASSWORD is null!!"),
	PASSWORD_FAIL("400", "PASSWORD is fail!!"), 
	PASSWORD_ERROR("400", "PASSWORD is Error !!"), 
	PASSWORD_UNREPEAT("400", "PASSWORD cannot repeat!!"), 
	NAME_NULL("400", "NAME is null!!"),
	NAME_FAIL("400", "NAME is fail!!"), 
	NAME_EXIST("400", "NAME is empty!!"), 
	BIRTHDAY_NULL("400", "BIRTHDAY is null!!"), 
	BIRTHDAY_FAIL("400", "BIRTHDAY is fail!!"), 
	GENDER_NULL("400", "GENDER is null!!"), 
	GENDER_FAIL("400", "GENDER is fail!!¡A½Ð¿é¤J¥N½XM / F "), 
	EMAIL_NULL("400", "EMAIL is null!!"), 
	EMAIL_FAIL("400", "EMAIL is fail!!");

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
