package com.example.appointment_system.ifs;

import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {

	// 建立病患資訊
	public AppointmentSystemRes createPatientInfo(String id, String password, String name, String birthday, String gender, String eMail);

	// 更改病患資訊---> ID  .password 判別.name eMail更改
	public AppointmentSystemRes updatePatientInfo(String id, String password, String name, String eMail);

	// 更改病患資訊---> ID . password判別 --> password 更改
	public AppointmentSystemRes updatePatientPassword(String id, String password,String newPassword);

	// 刪除病患資訊---> ID . password判別 --> 刪除
	public AppointmentSystemRes deletePatientPassword(String id, String password);
	
	//查詢病患基本訊息(不包含就診紀錄 跟 預約紀錄)
	public AppointmentSystemRes searchById (String id, String password);
	
	
	
}
