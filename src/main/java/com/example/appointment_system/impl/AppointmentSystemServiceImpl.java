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



	// �إ߯f�w���
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

	// ���f�w��T---> ID .password �P�O.name eMail���
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

	// ���f�w��T---> ID . password�P�O --> password ���
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

	// �R���f�w��T---> ID . password�P�O --> �R��
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

	// �z�LID�j�M (�ݭn�z�L�K�X���� ) -->�K�X���T�N�i�H�j�M�f�w��� �C
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
	// �P�_�榡 : �f�w�إ߸�Ʈ榡�O�_���T. �a�J�Ѽ� (id, password, name, birthday, gender, eMail)
	private AppointmentSystemRes checkCreateParams(String id, String password, String name, String birthday,
			String gender, String eMail) {

		// ------------------------�P�_��J�O�_����--------------------------//

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

		// -----------------------�P�_�����Ҫ��榡�O�_���T---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------�P�_�ͤ�榡�O�_���o-----------------------------//

		String birthdayPattern = "[1-2]{1}\\d{3}/[0-1]{1}\\d/\\d{2}";

		if (!birthday.matches(birthdayPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_FAIL.getMessage());
		}

		// ------------------------�P�O�ʧO�榡�O�_���o-----------------------------//

		String genderPattern = "[mfMF]{1}";

		if (!gender.matches(genderPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.GENDER_FAIL.getMessage());
		}

		// ------------------------�P�Oemail�榡�O�_���o-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// �P�_�榡 : �f�w����Ʈ榡�O�_���T. �a�J�Ѽ� (id, password, name, eMail)
	private AppointmentSystemRes checkUpdateParams(String id, String password, String name, String eMail) {

		// ------------------------�P�_��J�O�_����--------------------------//

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

		// -----------------------�P�_�����Ҫ��榡�O�_���T---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------�P�Oemail�榡�O�_���o-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!eMail.matches(emailPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// �P�_�榡 : �f�w����Ʈ榡�O�_���T. �a�J�Ѽ� (id, password, name, eMail)
	private AppointmentSystemRes checkIdPasswordParams(String id, String password) {

		// ------------------------�P�_��J�O�_����--------------------------//

		if (!StringUtils.hasText(id)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		// -----------------------�P�_�����Ҫ��榡�O�_���T---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!id.matches(idPattern)) {
			return new AppointmentSystemRes(null, AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		return null;

	}
}
