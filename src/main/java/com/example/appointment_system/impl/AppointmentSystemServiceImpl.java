package com.example.appointment_system.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.HospitalId;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
import com.example.appointment_system.vo.AppointmentSystemRes;

@Service
public class AppointmentSystemServiceImpl implements AppointmentSystemService {

//	@Autowired
//	private AppointmentDao appointmentDao;

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private HospitalDao hospitalDao;

//	@Autowired
//	private PatientDao patientDao;

//	新增醫院資訊
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

//	更新醫院資訊
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

//	刪除醫院資訊
	@Override
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId) {
		if(hospitalDao.findByHospitalId(hospitalId) == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}
		
		hospitalDao.deleteByHospitalId(hospitalId);

		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

//	刪除醫院科別
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

	// 新增醫生資訊
	@Override
	public AppointmentSystemRes createDoctorInfo(String hospitalId, String doctorId, String doctorName,
			String doctorDepartment, String appointmentTime) {
		AppointmentSystemRes appointmentSystemRes = new AppointmentSystemRes();
		String department = doctorDepartment;
		HospitalId hospitalIdd = new HospitalId(hospitalId, department);
		Optional<Hospital> hospitalIdOp = hospitalDao.findById(hospitalIdd);
		if (!hospitalIdOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_DEPARTMENT_INEXISTED.getMessage());
		}

		if (doctorDao.existsById(doctorId)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_EXISTED.getMessage());
		}

		Doctor doctorInfo = new Doctor(hospitalId, doctorId, doctorName, doctorDepartment, appointmentTime);
		doctorDao.save(doctorInfo);
		appointmentSystemRes.setMessage(AppointmentSystemRtnCode.CREATE_SUCCESSFUL.getMessage());
		appointmentSystemRes.setDoctor(doctorInfo);
		return appointmentSystemRes;
	}

	// 更新醫生資訊
	@Override
	public AppointmentSystemRes updateDoctorInfo(String doctorId, String doctorName, String doctorDepartment,
			String hospitalId, String appointmentTime) {
		AppointmentSystemRes appointmentSystemRes = new AppointmentSystemRes();
		Optional<Doctor> doctorOp = doctorDao.findById(doctorId);
		if (doctorOp.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_INEXISTED.getMessage());
		}

		Doctor doctor = doctorOp.get();

		if (StringUtils.hasText(doctorName)) {
			doctor.setDoctorName(doctorName);
		}
		if (StringUtils.hasText(doctorDepartment)) {
			String department = doctorDepartment;
			HospitalId hospitalIdd = new HospitalId(hospitalId, department);
			Optional<Hospital> hospitalIdOp = hospitalDao.findById(hospitalIdd);
			if (hospitalIdOp.isEmpty()) {
				return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_DEPARTMENT_INEXISTED.getMessage());
			}
			doctor.setDoctorDepartment(doctorDepartment);
		}
		if (StringUtils.hasText(hospitalId)) {
			String department = doctorDepartment;
			HospitalId hospitalIdd = new HospitalId(hospitalId, department);
			Optional<Hospital> hospitalIdOp = hospitalDao.findById(hospitalIdd);
			if (hospitalIdOp.isEmpty()) {
				return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_DEPARTMENT_INEXISTED.getMessage());
			}
			doctor.setHospitalId(hospitalId);
		}
		if (StringUtils.hasText(appointmentTime)) {
			doctor.setAppointmentTime(appointmentTime);
		}

		doctorDao.save(doctor);
		appointmentSystemRes.setMessage(AppointmentSystemRtnCode.UPDATE_SUCCESSFUL.getMessage());
		appointmentSystemRes.setDoctor(doctor);
		return appointmentSystemRes;
	}

	// 刪除醫生資訊
	@Override
	public Doctor deleteDoctorInfo(String doctorId) {
		Optional<Doctor> doctorOp = doctorDao.findById(doctorId);
		if (doctorOp.isEmpty()) {
			return null;
		}
		Doctor doctor = doctorOp.get();
		doctorDao.deleteById(doctorId);

		return doctor;
	}
}
