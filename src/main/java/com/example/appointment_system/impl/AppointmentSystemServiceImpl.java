package com.example.appointment_system.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.ifs.AppointmentSystemService;
//import com.example.appointment_system.respository.AppointmentDao;
//import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
//import com.example.appointment_system.respository.PatientDao;
import com.example.appointment_system.vo.AppointmentSystemRes;

@Service
public class AppointmentSystemServiceImpl implements AppointmentSystemService {

//	@Autowired
//	private AppointmentDao appointmentDao;
//
//	@Autowired
//	private DoctorDao doctorDao;

	@Autowired
	private HospitalDao hospitalDao;

//	@Autowired
//	private PatientDao patientDao;

	@Override
	public Hospital createHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address, String department) {

		if (hospitalDao.existsById(hospitalId)) {
			return null;
		}

		Hospital hospital = new Hospital(hospitalId, hospitalName, phone, city, district, address, department);
		return hospitalDao.save(hospital);
	}

	@Override
	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address, String department) {
		AppointmentSystemRes appointmentSystemRes = new AppointmentSystemRes();
		Optional<Hospital> hospitalOp = hospitalDao.findById(hospitalId);
		Hospital hospital = hospitalOp.get();

		if (!hospitalOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		setParams(hospital, hospitalName, phone, city, district, address, department);
		hospitalDao.save(hospital);
		appointmentSystemRes.setHospital(hospital);
		appointmentSystemRes.setMessage(AppointmentSystemRtnCode.SUCCESSFUL.getMessage());
		return appointmentSystemRes;
	}

	private void setParams(Hospital hospital, String hospitalName, String phone, String city, String district,
			String address, String department) {

		if (StringUtils.hasText(hospitalName)) {
			hospital.setHospitalName(hospitalName);
		}

		if (StringUtils.hasText(phone)) {
			hospital.setHospitalName(phone);
		}

		if (StringUtils.hasText(city)) {
			hospital.setHospitalName(city);
		}

		if (StringUtils.hasText(district)) {
			hospital.setHospitalName(district);
		}

		if (StringUtils.hasText(address)) {
			hospital.setHospitalName(address);
		}

		if (StringUtils.hasText(department)) {
			hospital.setHospitalName(department);
		}
	}

	@Override
	public Hospital deleteHospitalInfo(String hospitalId, String department) {
		
		if (hospitalDao.existsById(hospitalId)) {
			return null;
		}
		
		
		Hospital hospital = new Hospital(hospitalId, department);
//		return hospitalDao.delete(hospital);
		return null;
	}

}
