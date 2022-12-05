package com.example.appointment_system.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Patient;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.AppointmentDao;
import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
import com.example.appointment_system.respository.PatientDao;
import com.example.appointment_system.vo.AppointmentSystemRes;

@Service
public class AppointmentSystemServiceImpl implements AppointmentSystemService {

	@Autowired
	private AppointmentDao appointmentDao;

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private PatientDao patientDao;



	// 建立病患資料
	@Override
	public AppointmentSystemRes createPatientInfo(String id, String password, String name, String birthday,	String gender, String eMail) {

		AppointmentSystemRes checkCreateParams = checkCreateParams(id, password, name, birthday, gender, eMail);

		if (checkCreateParams != null) {
			
			return checkCreateParams;
		}

		if (patientDao.existsById(id)) {
			
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_EXIST.getMessage());
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
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (!patientDB.equalsIgnoreCase(password)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
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

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		//
		Patient patient = patientOp.get();
		String passwordDB = patient.getPassword();

		if (!passwordDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		if (passwordDB.contentEquals(newPassword)) {

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_UNREPEAT.getMessage());
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

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}
        
		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();
		
		if (patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		patientDao.deleteById(id);

		return new AppointmentSystemRes(null, AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());

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

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}
		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();
		
		if (patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		return new AppointmentSystemRes(patient, AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());

	}
	// 判斷格式 : 病患建立資料格式是否正確. 帶入參數 (id, password, name, birthday, gender, eMail)
	private AppointmentSystemRes checkCreateParams(String id, String password, String name, String birthday,
			String gender, String eMail) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(name)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(birthday)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.BIRTHDAY_NULL.getMessage());
		}

		if (!StringUtils.hasText(gender)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.GENDER_NULL.getMessage());
		}

		if (!StringUtils.hasText(eMail)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判斷生日格式是否正卻-----------------------------//

		String birthdayPattern = "[1-2]{1}\\d{3}/[0-1]{1}\\d/\\d{2}";

		if (!birthday.matches(birthdayPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_FAIL.getMessage());
		}

		// ------------------------判別性別格式是否正卻-----------------------------//

		String genderPattern = "[mfMF]{1}";

		if (!gender.matches(genderPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.GENDER_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正卻-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkUpdateParams(String id, String password, String name, String eMail) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(name)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(eMail)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正卻-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkIdPasswordParams(String id, String password) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		return null;

	}
}
