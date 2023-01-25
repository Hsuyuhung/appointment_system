package com.example.appointment_system.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Appointment;
import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.HospitalId;
import com.example.appointment_system.entity.Patient;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.AppointmentDao;
import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
import com.example.appointment_system.respository.PatientDao;
import com.example.appointment_system.vo.AppoinmentInfo;
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

	@Autowired
	private JavaMailSender emailSender;

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

		if (hospitalList.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_NONEXIST.getMessage());
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
		appointmentSystemRes.setHospitalList(hospitalList);
		return appointmentSystemRes;
	}

//	透過科別尋找醫院
	@Override
	public AppointmentSystemRes findHospitalInfoByDepartment(String department) {

		List<Hospital> hospitalList = hospitalDao.findByDepartment(department);
		if (hospitalList.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_WRONG.getMessage());
		}
		return new AppointmentSystemRes(hospitalList);
	}

//	透過科別、城市尋找醫院
	@Override
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCity(String department, String city) {

		List<Hospital> hospitalList = hospitalDao.findByDepartmentAndCity(department, city);

		if (hospitalList == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}

		return new AppointmentSystemRes(hospitalList);
	}

//	透過科別、城市、地區尋找醫院
	@Override
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCityAndDistrict(String department, String city,
			String district) {

		List<Hospital> hospitalList = hospitalDao.findByDepartmentAndCityAndDistrict(department, city, district);

		if (hospitalList == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}

		return new AppointmentSystemRes(hospitalList);
	}

//	刪除醫院資訊
	@Override
	public AppointmentSystemRes deleteHospitalInfo(String hospitalId) {

		List<Hospital> hospitalList = hospitalDao.findByHospitalId(hospitalId);
		if (hospitalList.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}

		hospitalDao.deleteAll(hospitalList);

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
			String doctorDepartment, String appointmentTime, String week) {
		AppointmentSystemRes appointmentSystemRes = new AppointmentSystemRes();
		String department = doctorDepartment;
		HospitalId hospitalIdd = new HospitalId(hospitalId, department);
		Optional<Hospital> hospitalIdOp = hospitalDao.findById(hospitalIdd);
		if (!hospitalIdOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_DEPARTMENT_INEXISTED.getMessage());
		}

		Optional<Doctor> doctorOp = doctorDao.findByDoctorIdAndAppointmentTimeAndWeek(doctorId, appointmentTime, week);
		if (doctorOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORAPPOINTMENTTIME_WEEK_EXISTED.getMessage());
		}

		Doctor doctorInfo = new Doctor(hospitalId, doctorId, doctorName, doctorDepartment, appointmentTime, week);
		doctorDao.save(doctorInfo);
		appointmentSystemRes.setMessage(AppointmentSystemRtnCode.CREATE_SUCCESSFUL.getMessage());
		appointmentSystemRes.setDoctor(doctorInfo);

		return appointmentSystemRes;
	}

	// 更新醫生資訊
	@Override
	public AppointmentSystemRes updateDoctorInfo(String doctorId, String hospitalId, String appointmentTime,
			String week, String newAppointmentTime, String newWeek) {
		Optional<Doctor> doctor = doctorDao.findByDoctorIdAndHospitalIdAndAppointmentTimeAndWeek(doctorId, hospitalId,
				appointmentTime, week);

		if (!doctor.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}

		Optional<Doctor> doctorOp = doctorDao.findByDoctorIdAndAppointmentTimeAndWeek(doctorId, newAppointmentTime,
				newWeek);
		if (doctorOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORAPPOINTMENTTIME_WEEK_EXISTED.getMessage());
		}
		Doctor doctorInfo = doctor.get();
		if (StringUtils.hasText(newAppointmentTime)) {
			doctorInfo.setAppointmentTime(newAppointmentTime);
		}
		if (StringUtils.hasText(newWeek)) {
			doctorInfo.setWeek(newWeek);
		}
		doctorDao.save(doctorInfo);
		return new AppointmentSystemRes(AppointmentSystemRtnCode.UPDATE_SUCCESSFUL.getMessage(), doctorInfo);
	}

	// 依科別找醫生資訊
	@Override
	public AppointmentSystemRes findByDoctorDepartment(String doctorDepartment) {
		List<Doctor> doctorList = doctorDao.findByDoctorDepartment(doctorDepartment);
		if (doctorList.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}
		return new AppointmentSystemRes(doctorList, null);
	}

	// 刪除醫生資訊
	@Override
	public AppointmentSystemRes deleteDoctorInfo(String doctorId, String hospitalId) {
		List<Doctor> doctorList = doctorDao.findByDoctorIdAndHospitalId(doctorId, hospitalId);
		if (doctorList == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_INEXISTED.getMessage());
		}

		doctorDao.deleteAll(doctorList);

		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	// 建立病患資料
	@Override
	public AppointmentSystemRes createPatientInfo(String patientId, String password, String confirmPassword,
			String patientName, String birthday, String gender, String email) {

		AppointmentSystemRes checkCreateParams = checkCreateParams(patientId, password, patientName, birthday, gender,
				email);

		if (checkCreateParams != null) {

			return checkCreateParams;
		}

		if (patientDao.existsById(patientId)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EXIST.getMessage());
		}

		if (!password.equals(confirmPassword)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_CONFIRM.getMessage());
		}

		Patient patient = new Patient(patientId, password, patientName, birthday, gender, email);
		patientDao.save(patient);

		return new AppointmentSystemRes(patient);
	}

	// 更改病患資訊---> ID .password 判別.name eMail更改
	@Override
	public AppointmentSystemRes updatePatientInfo(String patientId, String password, String patientName, String email) {

		AppointmentSystemRes checkUpdateParams = checkUpdateParams(patientId, password, patientName, email);

		if (checkUpdateParams != null) {
			return checkUpdateParams;
		}

		Optional<Patient> patientOp = patientDao.findById(patientId);

		if (!patientOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (!patientDB.equalsIgnoreCase(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		patient.setEmail(email);
		patient.setPatientName(patientName);
		patientDao.save(patient);
		// 更新回傳資訊12/19
		return new AppointmentSystemRes(patient);
	}

	// 更改病患資訊---> ID . password判別 --> password 更改
	@Override
	public AppointmentSystemRes updatePatientPassword(String patientId, String password, String newPassword) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(patientId, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}

		Optional<Patient> patientOp = patientDao.findById(patientId);

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

		return new AppointmentSystemRes(patient);

	}

	// 刪除病患資訊---> ID . password判別 --> 刪除
	@Override
	public AppointmentSystemRes deletePatientPassword(String patientId, String password) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(patientId, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}

		Optional<Patient> patientOp = patientDao.findById(patientId);

		if (!patientOp.isPresent()) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (!patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		patientDao.deleteById(patientId);

		return new AppointmentSystemRes(patient);

	}

	// 透過ID搜尋 (需要透過密碼驗證 ) -->密碼正確就可以搜尋病患資料 。
	@Override
	public AppointmentSystemRes searchById(String patientId, String password) {

		AppointmentSystemRes checkUpdatePasswordParams = checkIdPasswordParams(patientId, password);

		if (checkUpdatePasswordParams != null) {
			return checkUpdatePasswordParams;
		}
		Optional<Patient> patientOp = patientDao.findById(patientId);

		if (!patientOp.isPresent()) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}
		Patient patient = patientOp.get();
		String patientDB = patient.getPassword();

		if (!patientDB.equalsIgnoreCase(password)) {

			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_ERROR.getMessage());
		}

		return new AppointmentSystemRes(patient);

	}

	// 建立預約系統12/16 更新
	@Override
	public AppointmentSystemRes creatAppointmentSystem(String patientId, String doctorId, String hospitalName,
			String appointmentTime, String week, LocalDate appointmentDate) {

		// 在資料庫中取得病人的資訊
		Optional<Patient> patientOp = patientDao.findById(patientId);
		// 如果沒有病患資訊則返回無此病人
		if (!patientOp.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}
		// 在資料庫中取醫院的資訊
		List<Hospital> hospitalList = hospitalDao.findByHospitalName(hospitalName);

		for (Hospital i : hospitalList) {
			String checkhospital = i.getHospitalName();

			if (!checkhospital.equalsIgnoreCase(hospitalName)) {
				return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_DEPARTMENT_INEXISTED.getMessage());
			}
		}
		// 如果沒有病患資訊則返回無此病人
		List<Doctor> checkDoctorList = doctorDao.findByDoctorId(doctorId);
		for (Doctor j : checkDoctorList) {
			String checkweek = j.getWeek();
			String checkTime = j.getAppointmentTime();
			if (checkTime.equalsIgnoreCase(appointmentTime)) {
				if (!checkweek.equalsIgnoreCase(week)) {
					return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_INEXISTED.getMessage());
				}
			}
		}
		// 在資料庫搜尋有沒有醫生的資訊 ，並且取出醫生的資訊(判斷方式用醫生的ID 搭配 診別 + 星期)
//		Optional<Doctor> checkDoctor = doctorDao.findByDoctorIdAndAppointmentTimeAndWeek(doctorId, appointmentTime,
//				week);
//
//		// 如果這個醫生再輸入的星期跟輸入的診別沒有上班，那回傳清單為null則返回且回傳訊息沒有此醫生
//		if (!checkDoctor.isPresent()) {
//			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_INEXISTED.getMessage());
//		}

		// 12/19更新 把week改成int跟輸入的date取得的星期做比較 如果 不一樣 則跳處錯誤訊息
		int i = Integer.valueOf(week);
		if (appointmentDate.getDayOfWeek().getValue() != i) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.CHECK_WEEK.getMessage());
		}

		List<Appointment> appointmentTimeList = appointmentDao
				.findByDoctorIdAndAppointmentTimeAndAppointmentDate(doctorId, appointmentTime, appointmentDate);

		for (Appointment item : appointmentTimeList) {
			String checkID = item.getPatientId();

			if (checkID.equals(patientId)) {

				return new AppointmentSystemRes(AppointmentSystemRtnCode.CREATE_DOUBLE_APPOINMENT_NULL.getMessage());
			}
		}
		// size 計算多少人數在個診間
		int size = appointmentTimeList.size();
		// 如果大於30人就顯示額滿
		if (size > 3) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.CREATE_APPOINMENT_NULL.getMessage());
			// 沒有的話則建立預約
		} else {
			LocalDateTime createTime = LocalDateTime.now();
			Appointment Appointment = new Appointment(patientId, doctorId, hospitalName, appointmentTime, week,
					appointmentDate, createTime);
			appointmentDao.save(Appointment);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(patientOp.get().getEmail());
			message.setSubject("預約成功");
			message.setText("預約成功，您的預約資訊如下：\n 帳號:" + Appointment.getPatientId().toString() + "\n醫生ID:"
					+ Appointment.getDoctorId().toString() + "\n預約看診日期:" + Appointment.getAppointmentDate().toString()
					+ "\n診別:" + Appointment.getAppointmentTime().toString() + "\n星期:" + Appointment.getWeek().toString()
					+ "\n預約時間:" + Appointment.getCreateTime().toString());
			emailSender.send(message);
			return new AppointmentSystemRes(Appointment, null);
		}
	}

	// 判斷格式 : 病患建立資料格式是否正確. 帶入參數 (id, password, name, birthday, gender, eMail)
	private AppointmentSystemRes checkCreateParams(String patientId, String password, String patientName,
			String birthday, String gender, String email) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(patientId)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(patientName)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(birthday)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.BIRTHDAY_NULL.getMessage());
		}

		if (!StringUtils.hasText(gender)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.GENDER_NULL.getMessage());
		}

		if (!StringUtils.hasText(email)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!patientId.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判別性別格式是否正確-----------------------------//

		if (!gender.matches("(男|女){1}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.GENDER_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正確-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!email.matches(emailPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}

		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkUpdateParams(String patientId, String password, String patientName,
			String email) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(patientId)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		if (!StringUtils.hasText(patientName)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NAME_NULL.getMessage());
		}

		if (!StringUtils.hasText(email)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!patientId.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		// ------------------------判別email格式是否正卻-----------------------------//

		String emailPattern = "[A-Za-z0-9+_.-]+@(.+)$";

		if (!email.matches(emailPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.EMAIL_FAIL.getMessage());
		}
		return null;

	}

	// 判斷格式 : 病患更改資料格式是否正確. 帶入參數 (id, password, name, eMail)
	private AppointmentSystemRes checkIdPasswordParams(String patientId, String password) {

		// ------------------------判斷輸入是否有值--------------------------//

		if (!StringUtils.hasText(patientId)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(password)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}

		// -----------------------判斷身分證的格式是否正確---------------------------//

		String idPattern = "[A-Z][1-2]\\d{8}";

		if (!patientId.matches(idPattern)) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		return null;

	}

	// 搜尋預約
	@Override
	public AppointmentSystemRes findAppointment(String patientId) {
		List<Appointment> appointmentList = appointmentDao.findAllByPatientId(patientId);
		
		System.out.println(appointmentList.size());
		if (appointmentList.isEmpty()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
		}

//		List<String> doctorIdList = new ArrayList<>();
//
//		appointmentList.forEach((e) -> {
//			doctorIdList.add(e.getDoctorId());
//		});
		List<Doctor> doctorList = doctorDao.findAll();

		for (Doctor item : doctorList) {
			item.setAutoId(null);
			item.setHospitalId(null);
			item.setAppointmentTime(null);
			item.setWeek(null);
		}

		List<AppoinmentInfo> appoinmentInfoList = new ArrayList<>();
		for (int i = 0; i < appointmentList.size(); i++) {
			Doctor doctor = new Doctor();
			for(Doctor item: doctorList) {
				if(item.getDoctorId().equals(appointmentList.get(i).getDoctorId())) {
					doctor = item;
				
				AppoinmentInfo appoinmentInfo = new AppoinmentInfo(appointmentList.get(i).getAutoId(),
						appointmentList.get(i).getHospitalName(), doctor,
						appointmentList.get(i).getAppointmentDate(), appointmentList.get(i).getWeek(),
						appointmentList.get(i).getAppointmentTime(), appointmentList.get(i).getCreateTime());
				appoinmentInfoList.add(appoinmentInfo);
				break;
				}
			}
//			AppoinmentInfo appoinmentInfo = new AppoinmentInfo(appointmentList.get(i).getAutoId(),
//					appointmentList.get(i).getHospitalName(), doctor,
//					appointmentList.get(i).getAppointmentDate(), appointmentList.get(i).getWeek(),
//					appointmentList.get(i).getAppointmentTime(), appointmentList.get(i).getCreateTime());
//			appoinmentInfoList.add(appoinmentInfo);
		}
		System.out.println(appoinmentInfoList.size());
		Optional<Patient> patient = patientDao.findById(patientId);
		if (patient != null && patient.isPresent()) {
			Patient patientInfo = patient.get();
			patientInfo.setPatientId(null);
			patientInfo.setPassword(null);
			return new AppointmentSystemRes(null, patientInfo, appoinmentInfoList);
		}
		return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());

	}

	// 刪除預約
	@Override
	public AppointmentSystemRes deleteAppointment(int autoId) {

		Optional<Appointment> deleteAppointmentList = appointmentDao.findById(autoId);

		if (!deleteAppointmentList.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.NOT_FIND.getMessage());
		}

		appointmentDao.deleteById(autoId);
		return new AppointmentSystemRes(AppointmentSystemRtnCode.DELETE_SUCCESSFUL.getMessage());
		
//		List<Appointment> appointmentList = appointmentDao.findAllByPatientId(patientId);
//
//		if (appointmentList.isEmpty()) {
//			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
//		}
//
//		List<String> doctorIdList = new ArrayList<>();
//
//		appointmentList.forEach((e) -> {
//			doctorIdList.add(e.getDoctorId());
//		});
//		List<Doctor> doctorList = doctorDao.findAllBydoctorIdIn(doctorIdList);
//
//		for (Doctor item : doctorList) {
//			item.setAutoId(null);
//			item.setHospitalId(null);
//			item.setAppointmentTime(null);
//			item.setWeek(null);
//		}
//
//		List<AppoinmentInfo> appoinmentInfoList = new ArrayList<>();
//		for (int i = 0; i < appointmentList.size()-1; i++) {
//			AppoinmentInfo appoinmentInfo = new AppoinmentInfo(appointmentList.get(i).getAutoId(),
//					appointmentList.get(i).getHospitalName(), doctorList.get(i),
//					appointmentList.get(i).getAppointmentDate(), appointmentList.get(i).getWeek(),
//					appointmentList.get(i).getAppointmentTime(), appointmentList.get(i).getCreateTime());
//			appoinmentInfoList.add(appoinmentInfo);
//		}
//
//		Optional<Patient> patient = patientDao.findById(patientId);
//		if (patient != null && patient.isPresent()) {
//			Patient patientInfo = patient.get();
//			patientInfo.setPatientId(null);
//			patientInfo.setPassword(null);
//			return new AppointmentSystemRes(null, patientInfo, appoinmentInfoList);
//		}
//		return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_EMPTY.getMessage());
	}

	// 登入
	@Override
	public AppointmentSystemRes login(String patientId, String password) {
		Optional<Patient> patient = patientDao.findById(patientId);

		if (patientId.equals("A111111111") && password.equals("1234567")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ADMINISTRATOR_LOGIN_SUCCESSFUL.getMessage());
		}

		if (patient.isPresent()) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.USER_LOGIN_SUCCESSFUL.getMessage());
		}

		return new AppointmentSystemRes(AppointmentSystemRtnCode.ACCOUNT_OR_PASSWORD_WRONG.getMessage());
	}

}