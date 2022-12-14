package com.example.appointment_system.ifs;

import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.vo.AppointmentSystemRes;	

public interface AppointmentSystemService {

//	創建醫院資訊
	public Hospital createHospitalInfo(String hospitalId, String department, String hospitalName, String phone,
			String city, String district, String address);

//	更新醫院資訊
	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address);

//	透過科別查詢醫院
	public AppointmentSystemRes findHospitalInfoByDepartment(String department);

//	透過科別，城市查詢醫院
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCity(String department, String city);

//	透過科別、城市、區域查詢醫院
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCityAndDistrict(String department, String city,
			String district);

//	刪除醫院資訊
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId);

//	創建醫院科別
	public AppointmentSystemRes deleteHospitalDepartment(String hospitalId, String department);

	// doctor1 : 創建醫生資訊
	public AppointmentSystemRes createDoctorInfo(String hospitalId, String doctorId, String doctorName,
			String doctorDepartment, String appointmentTime, String week);

	// doctor2 : 更新醫生資訊(科別、看診時間跟星期不可改動)
	public AppointmentSystemRes updateDoctorInfo(String doctorId, String doctorName, String doctorDepartment,
			String hospitalId);

	// doctor3 : 刪除醫生資訊
	public AppointmentSystemRes deleteDoctorInfo(String doctorId);

	// 建立病患資訊
	public AppointmentSystemRes createPatientInfo(String patientId, String password, String patientName,
			String birthday, String gender, String email);

	// 更改病患資訊---> ID .password 判別.name eMail更改
	public AppointmentSystemRes updatePatientInfo(String patientId, String password, String patientName, String email);

	// 更改病患登入密碼---> ID . password判別 --> password 更改
	public AppointmentSystemRes updatePatientPassword(String patientId, String password, String newPassword);

	// 刪除病患資訊---> ID . password判別 --> 刪除
	public AppointmentSystemRes deletePatientPassword(String patientId, String password);

	// 查詢病患基本訊息(不包含就診紀錄 跟 預約紀錄)
	public AppointmentSystemRes searchById(String patientId, String password);

//	// 建立預約
//	public AppointmentSystemRes creatAppointment(String patientId, String birthday, String doctorId, String appointmentTime,
//			String week, LocalDate appointmentDate, String department, LocalDateTime createTime, String email);
	
//	搜尋預約
	public AppointmentSystemRes findAppointment(String patientId);
	
//	刪除預約
	public AppointmentSystemRes deleteAppointment(int autoId);

	//依科別搜尋醫生
	public AppointmentSystemRes findByDoctorDepartment(String doctorDepartment);
}