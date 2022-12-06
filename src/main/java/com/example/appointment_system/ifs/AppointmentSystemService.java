package com.example.appointment_system.ifs;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {
	
//	創建醫院資訊
	public Hospital createHospitalInfo(String hospitalId, String department, String hospitalName, String phone,
			String city, String district, String address);

//	更新醫院資訊
	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address);
	
//	刪除醫院資訊
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId);

//	創建醫院科別
	public AppointmentSystemRes deleteHospitalDepartment(String hospitalId, String department);
	
	//doctor1 :　創建醫生資訊
	public AppointmentSystemRes createDoctorInfo(String hospitalId, String doctorId, String doctorName, String doctorDepartment,
			String appointmentTime);
	
	//doctor2 : 更新醫生資訊
	public AppointmentSystemRes updateDoctorInfo(String doctorId, String doctorName, String doctorDepartment, String hospitalId,String appointmentTime);

	//doctor3 : 刪除醫生資訊
	public Doctor deleteDoctorInfo(String doctorId);

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
