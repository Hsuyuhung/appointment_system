package com.example.appointment_system.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.HospitalId;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.HospitalDao;
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
	public Hospital createHospitalInfo(String hospitalId, String department, String hospitalName, String phone,
			String city, String district, String address) {

		HospitalId hospitalPk = new HospitalId(hospitalId, department);
		Optional<Hospital> hospitalOp = hospitalDao.findById(hospitalPk);

		if (hospitalOp.isPresent()) {
			return null;
		}

		Hospital hospital = new Hospital(hospitalId, department, hospitalName, phone, city, district, address);
		return hospitalDao.save(hospital);
	}

	@Override
	public AppointmentSystemRes updateHospitalInfo(String hospitalId, String hospitalName, String phone, String city,
			String district, String address) {
		
		AppointmentSystemRes appointmentSystemRes = new AppointmentSystemRes();

		List<Hospital> hospitalList = hospitalDao.findByHospitalId(hospitalId);

		if (hospitalList == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		for (Hospital item : hospitalList) {

			if (StringUtils.hasText(hospitalName)) {
				item.setHospitalName(hospitalName);
			}

			if (StringUtils.hasText(phone)) {
				item.setPhone(phone);
			}

			if (StringUtils.hasText(city)) {
				item.setCity(city);
			}

			if (StringUtils.hasText(district)) {
				item.setDistrict(district);
			}

			if (StringUtils.hasText(address)) {
				item.setAddress(address);
			}
		}

		hospitalDao.saveAll(hospitalList);
		appointmentSystemRes.setMessage(AppointmentSystemRtnCode.UPDATE_SUCCESSFUL.getMessage());
		appointmentSystemRes.setHospitalList(hospitalList);
		return appointmentSystemRes;
	}


	@Override
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId) {
		hospitalDao.deleteByHospitalId(hospitalId);

		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	@Override
	public AppointmentSystemRes deleteHospitalDepartment(String hospitalId, String department) {

		HospitalId hospitalPk = new HospitalId(hospitalId, department);
		Optional<Hospital> hospitalOp = hospitalDao.findById(hospitalPk);

		if (!hospitalOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_WRONG.getMessage());
		}
		hospitalDao.deleteById(hospitalPk);
		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

}
