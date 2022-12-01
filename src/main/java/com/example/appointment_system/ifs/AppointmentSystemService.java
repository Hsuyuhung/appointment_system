package com.example.appointment_system.ifs;

import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {

	public Hospital createHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address, String department);

	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city, String district, String address,
			String department);

	public Hospital deleteHospitalInfo(String hospitalId, String department);
}
