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
import com.example.appointment_system.entity.Patient;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
import com.example.appointment_system.respository.PatientDao;
import com.example.appointment_system.vo.AppointmentSystemRes;

@Service
public class AppointmentSystemServiceImpl implements AppointmentSystemService {

//	@Autowired
//	private AppointmentDao appointmentDao;

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private PatientDao patientDao;

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
		if (hospitalDao.findByHospitalId(hospitalId) == null) {
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

	// 建立病患資料
	@Override
	public AppointmentSystemRes createPatientInfo(String id, String password, String name, String birthday,
			String gender, String eMail) {

		AppointmentSystemRes checkCreateParams = checkCreateParams(id, password, name, birthday, gender, eMail);

		if (checkCreateParams != null) {

			return checkCreateParams;
		}

		if (patientDao.existsById(id)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EXIST.getMessage());
		}

		Patient patient = new Patient(id, password, name, birthday, gender, eMail);
		patientDao.save(patient);

		return new AppointmentSystemRes(patient, AppointmentSystemRtnCode.SUCCESSFUL.getMessage());
	}

	// 更改病患資訊---> ID .password 判別.name eMail更改
	@Override
	public AppointmentSystemRes updatePatientInfo(String id, String password, String name, String eMail) {

		AppointmentSystemRes checkUpdateParams = checkUpdateParams(id, password, name, eMail);

		if (checkUpdateParams != null) {
			return checkUpdateParams;
		}

		Optional<Patient> patientOp = patientDao.findById(id);

		if (!patientOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (!patientDB.equalsIgnoreCase(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		patient.seteMail(eMail);
		patient.setName(name);
		patientDao.save(patient);
		return new AppointmentSystemRes(patient, AppointmentSystemRtnCode.UPDATE_SUCCESSFUL.getMessage());
	}

	// 更改病患資訊---> ID . password判別 --> password 更改
	@Override
	public AppointmentSystemRes updatePatientPassword(String id, String password, String newPassword) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(id, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}

		Optional<Patient> patientOp = patientDao.findById(id);

		if (!patientOp.isPresent()) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		//
		Patient patient = patientOp.get();
		String passwordDB = patient.getPassword();

		if (!passwordDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		if (passwordDB.contentEquals(newPassword)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_UNREPEAT.getMessage());
		}

		patient.setPassword(newPassword);
		patientDao.save(patient);

		return new AppointmentSystemRes(patient, AppointmentSystemRtnCode.UPDATE_SUCCESSFUL.getMessage());

	}

	// 刪除病患資訊---> ID . password判別 --> 刪除
	@Override
	public AppointmentSystemRes deletePatientPassword(String id, String password) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(id, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}

		Optional<Patient> patientOp = patientDao.findById(id);

		if (!patientOp.isPresent()) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		patientDao.deleteById(id);

		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());

	}

	// 透過ID搜尋 (需要透過密碼驗證 ) -->密碼正確就可以搜尋病患資料 。
	@Override
	public AppointmentSystemRes searchById(String id, String password) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(id, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}
		Optional<Patient> patientOp = patientDao.findById(id);

		if (!patientOp.isPresent()) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}
		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		return new AppointmentSystemRes(patient, AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());

	}

	// 判斷格式 : 病患建立資料格式是否正確. 帶入參數 (id, password, name, birthday, gender, eMail)
	private AppointmentSystemRes checkCreateParams(String id, String password, String name, String birthday,
			String gender, String eMail) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(name)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(birthday)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.BIRTHDAY_NULL.getMessage());
		}

		if (!StringUtils.hasText(gender)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.GENDER_NULL.getMessage());
		}

		if (!StringUtils.hasText(eMail)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判斷生日格式是否正卻-----------------------------//

		String birthdayPattern = "[1-2]{1}\\d{3}/[0-1]{1}\\d/\\d{2}";

		if (!birthday.matches(birthdayPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_FAIL.getMessage());
		}

		// ------------------------判別性別格式是否正卻-----------------------------//

		String genderPattern = "[mfMF]{1}";

		if (!gender.matches(genderPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.GENDER_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正卻-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkUpdateParams(String id, String password, String name, String eMail) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(name)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(eMail)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正卻-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkIdPasswordParams(String id, String password) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		return null;

	}
}