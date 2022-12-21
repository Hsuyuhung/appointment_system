package com.example.appointment_system.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.Patient;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.vo.AppointmentSystemRes;
import com.example.appointment_system.vo.AppointmentSystemReq;

@CrossOrigin
@RestController
public class AppointmentSystemController {

	@Autowired
	private AppointmentSystemService appointmentSystemService;

//	建立醫院資訊
	@PostMapping(value = "api/create_hospital_info")
	public AppointmentSystemRes createHospitalInfo(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getHospitalName())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_NAME_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getPhone())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_PHONE_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getCity())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_CITY_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getDistrict())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DISTRICT_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getAddress())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ADDRESS_EMPTY.getMessage());
		}

		if (!req.getHospitalId().matches("[A-Z]\\d{3}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		if (!req.getPhone().matches("\\d{2}-\\d{3}-\\d{4}|\\d{2}-\\d{4}-\\d{4}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_PHONE_WRONG.getMessage());
		}

		Hospital hospital = appointmentSystemService.createHospitalInfo(req.getHospitalId(), req.getDepartment(),
				req.getHospitalName(), req.getPhone(), req.getCity(), req.getDistrict(), req.getAddress());
		if (hospital == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EXSITED.getMessage());
		}
		return new AppointmentSystemRes(hospital);
	}

//	更新醫院資訊
	@PostMapping(value = "api/update_hospital_info")
	public AppointmentSystemRes updateHospitalInfo(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EMPTY.getMessage());
		}

		if (!req.getHospitalId().matches("[A-Z]\\d{3}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		if (!StringUtils.hasText(req.getHospitalName()) && !StringUtils.hasText(req.getPhone())
				&& !StringUtils.hasText(req.getCity()) && !StringUtils.hasText(req.getDistrict())
				&& !StringUtils.hasText(req.getAddress())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_UPDATE_EMPTY.getMessage());
		}

		return appointmentSystemService.updateHospitalInfo(req.getHospitalId(), req.getHospitalName(), req.getPhone(),
				req.getCity(), req.getDistrict(), req.getAddress());
	}

//	透過科別尋找醫院
	@PostMapping(value = "api/find_hospital_info_by_department")
	public AppointmentSystemRes findHospitalInfoByDepartment(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

		return appointmentSystemService.findHospitalInfoByDepartment(req.getDepartment());
	}

//	透過科別、城市尋找醫院
	@PostMapping(value = "api/find_hospital_info_by_department_and_city")
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCity(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getCity())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_CITY_EMPTY.getMessage());
		}

		return appointmentSystemService.findHospitalInfoByDepartmentAndCity(req.getDepartment(), req.getCity());
	}

//	透過科別、城市、地區尋找醫院
	@PostMapping(value = "api/find_hospital_info_by_department_and_city_and_district")
	public AppointmentSystemRes findHospitalInfoByDepartmentAndCityAndDistrict(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getCity())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_CITY_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getDistrict())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DISTRICT_EMPTY.getMessage());
		}

		return appointmentSystemService.findHospitalInfoByDepartmentAndCityAndDistrict(req.getDepartment(),
				req.getCity(), req.getDistrict());
	}

//	刪除此醫院所有資料
	@PostMapping(value = "api/delete_hospital_info")
	public AppointmentSystemRes deleteHospitalInfo(@RequestBody AppointmentSystemReq req) {

		if (!req.getHospitalId().matches("[A-Z]\\d{3}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EMPTY.getMessage());
		}

		return appointmentSystemService.deleteHospitalInfo(req.getHospitalId());
	}

//	刪除此醫院科別
	@PostMapping(value = "api/delete_hospital_department")
	public AppointmentSystemRes deleteHospitalDepartment(@RequestBody AppointmentSystemReq req) {

		if (!req.getHospitalId().matches("[A-Z]\\d{3}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		}

		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EMPTY.getMessage());
		}

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

		return appointmentSystemService.deleteHospitalDepartment(req.getHospitalId(), req.getDepartment());

	}

	// 新增醫生資訊
	@PostMapping(value = "/api/createDoctorInfo")
	public AppointmentSystemRes createDoctorInfo(@RequestBody AppointmentSystemReq req) {
		AppointmentSystemRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}

		AppointmentSystemRes doctor = appointmentSystemService.createDoctorInfo(req.getHospitalId(), req.getDoctorId(),
				req.getDoctorName(), req.getDoctorDepartment(), req.getAppointmentTime(), req.getWeek());

		return doctor;
	}

	// 新增醫生資訊 checkParam
	private AppointmentSystemRes checkParam(AppointmentSystemReq req) {
		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorName())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORNAME_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORDEPARTMENT_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getAppointmentTime())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORAPPOINTMENTTIME_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getWeek())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.WEEK_REQUIRED.getMessage());
		} else if (!req.getDoctorId().matches("[A-Z]\\d{6}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTOR_ID_EXISTED.getMessage());
		}
		return null;
	}

	// 更新醫生資訊
	@PostMapping(value = "/api/updateDoctorInfo")
	public AppointmentSystemRes updateDoctorInfo(@RequestBody AppointmentSystemReq req) {
		AppointmentSystemRes doctor = appointmentSystemService.updateDoctorInfo(req.getDoctorId(), req.getHospitalId(),
				req.getAppointmentTime(), req.getWeek(), req.getNewAppointmentTime(), req.getNewWeek());
		if (doctor == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_REQUIRED.getMessage());
		}
		if (!StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITALID_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_REQUIRED.getMessage());
		}  else if (!StringUtils.hasText(req.getAppointmentTime())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORAPPOINTMENTTIME_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getWeek())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.WEEK_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getNewAppointmentTime())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORAPPOINTMENTTIME_REQUIRED.getMessage());
		} else if (!StringUtils.hasText(req.getNewWeek())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.WEEK_REQUIRED.getMessage());
		}else if (!req.getDoctorId().matches("[A-Z]\\d{6}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTOR_ID_EXISTED.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorId()) && !StringUtils.hasText(req.getHospitalId())
				&& !StringUtils.hasText(req.getAppointmentTime()) && !StringUtils.hasText(req.getWeek())) {
			return new AppointmentSystemRes(
					AppointmentSystemRtnCode.DOCTORNAME_DOCTORDEPARTMENT_HOSPITALID_REQUIRED.getMessage());
		}
		return doctor;

	}

	// 依科別搜尋醫生
	@PostMapping(value = "/api/findByDoctorDepartment")
	public AppointmentSystemRes findByDoctorDepartment(@RequestBody AppointmentSystemReq req) {
		if (!StringUtils.hasText(req.getDoctorDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORDEPARTMENT_REQUIRED.getMessage());
		}
		return appointmentSystemService.findByDoctorDepartment(req.getDoctorDepartment());
	}

	// 刪除醫生資訊
	@PostMapping(value = "/api/deleteDoctorInfo")
	public AppointmentSystemRes deleteDoctorInfo(@RequestBody AppointmentSystemReq req) {
		if (!req.getDoctorId().matches("[A-Z]\\d{6}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTOR_ID_EXISTED.getMessage());
		}
		if (!req.getHospitalId().matches("[A-Z]\\d{3}")) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_WRONG.getMessage());
		} else if (!StringUtils.hasText(req.getDoctorId()) && !StringUtils.hasText(req.getHospitalId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.DOCTORID_REQUIRED.getMessage());
		}
		return appointmentSystemService.deleteDoctorInfo(req.getDoctorId(), req.getHospitalId());
	}

	// 建立病患資訊
	@PostMapping(value = "/api/createPatientInfo")
	public AppointmentSystemRes createPatientInfo(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.createPatientInfo(req.getPatientId(), req.getPassword(),
				req.getConfirmPassword(), req.getPatientName(), req.getBirthday(), req.getGender(), req.getEmail());
	}

	// 更改病患資訊---> ID .password 判別.name eMail更改
	@PostMapping(value = "/api/updatePatientInfo")
	public AppointmentSystemRes updatePatientInfo(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.updatePatientInfo(req.getPatientId(), req.getPassword(), req.getPatientName(),
				req.getEmail());
	}

	// 更改病患資訊---> ID . password判別 --> password 更改
	@PostMapping(value = "/api/updatePatientPassword")
	public AppointmentSystemRes updatePatientPassword(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.updatePatientPassword(req.getPatientId(), req.getPassword(),
				req.getNewPassword());
	}

	// 刪除病患資訊---> ID . password判別 --> 刪除
	@PostMapping(value = "/api/deletePatientPassword")
	public AppointmentSystemRes deletePatientPassword(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.deletePatientPassword(req.getPatientId(), req.getPassword());
	}

	// 查詢病患基本訊息(不包含就診紀錄 跟 預約紀錄)
	@PostMapping(value = "/api/searchById")
	public AppointmentSystemRes searchById(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.searchById(req.getPatientId(), req.getPassword());
	}

//	創建預約
	@PostMapping(value = "/api/creatAppointmentSystem")
	public AppointmentSystemRes creatAppointment(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.creatAppointmentSystem(req.getPatientId(), req.getDoctorId(),
				req.getHospitalName(), req.getAppointmentTime(), req.getWeek(), req.getAppointmentDate());
	}

//	搜尋預約
	@PostMapping(value = "/api/find_Appointment")
	public AppointmentSystemRes findAppointment(@RequestBody AppointmentSystemReq req) {

		if (!StringUtils.hasText(req.getPatientId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_FAIL.getMessage());
		}

		return appointmentSystemService.findAppointment(req.getPatientId());
	}

//	刪除預約
	@PostMapping(value = "/api/delete_appointment")
	public AppointmentSystemRes deleteAppointment(@RequestBody AppointmentSystemReq req) {

		if (req.getChartNo() == 0) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.APPOINMENT_CHART_EMPTY.getMessage());
		}

		return appointmentSystemService.deleteAppointment(req.getChartNo());
	}

	// 登入
	@PostMapping(value = "/api/login")
	public AppointmentSystemRes login(@RequestBody AppointmentSystemReq req, HttpSession httpSession) {

		if (!StringUtils.hasText(req.getPatientId())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.ID_NULL.getMessage());
		}

		if (!StringUtils.hasText(req.getPassword())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.PASSWORD_NULL.getMessage());
		}
		Patient patient = new Patient(req.getPatientId(), req.getPassword());
		httpSession.setAttribute("patientInfo", patient);
		return appointmentSystemService.login(req.getPatientId(), req.getPassword());
	}
}
