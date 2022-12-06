package com.example.appointment_system.ifs;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {

	public Hospital createHospitalInfo(String hospitalId, String department, String hospitalName, String phone,
			String city, String district, String address);

	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address);
	
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId);

	public AppointmentSystemRes deleteHospitalDepartment(String hospitalId, String department);
	
	//doctor1 :　創建醫生資訊
	public AppointmentSystemRes createDoctorInfo(String hospitalId, String doctorId, String doctorName, String doctorDepartment,
			String appointmentTime);
	
	//doctor2 : 更新醫生資訊
	public AppointmentSystemRes updateDoctorInfo(String doctorId, String doctorName, String doctorDepartment, String hospitalId,String appointmentTime);

	//doctor3 : 刪除醫生資訊
	public Doctor deleteDoctorInfo(String doctorId);
}
