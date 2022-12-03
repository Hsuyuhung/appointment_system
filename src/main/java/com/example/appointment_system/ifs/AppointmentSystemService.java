package com.example.appointment_system.ifs;

import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {

	public Hospital createHospitalInfo(String hospitalId, String department, String hospitalName, String phone,
			String city, String district, String address);

	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address);
	
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId);

	public AppointmentSystemRes deleteHospitalDepartment(String hospitalId, String department);
}
