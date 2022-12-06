package com.example.appointment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment_system.constants.AppointmentSystemRtnCode;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.vo.AppointmentSystemRes;
import com.example.appointment_system.vo.AppointmentSystemReq;

@RestController
public class AppointmentSystemController {

	@Autowired
	private AppointmentSystemService appointmentSystemService;

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
		return new AppointmentSystemRes(AppointmentSystemRtnCode.CREATE_SUCCESSFUL.getMessage(), hospital);
	}

	@PostMapping(value = "api/update_hospital_info")
	public AppointmentSystemRes updateHospitalInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.updateHospitalInfo(req.getHospitalId(), req.getHospitalName(), req.getPhone(),
				req.getCity(), req.getDistrict(), req.getAddress());
	}

	@PostMapping(value = "api/delete_hospital_info")
	public AppointmentSystemRes deleteHospitalInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.deleteHospitalInfo(req.getHospitalId());
	}

	@PostMapping(value = "api/delete_hospital_department")
	public AppointmentSystemRes deleteHospitalDepartment(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.deleteHospitalDepartment(req.getHospitalId(), req.getDepartment());
	}

	// 建立病患資訊
	@PostMapping(value = "/api/createPatientInfo")
	public AppointmentSystemRes createPatientInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.createPatientInfo(req.getId(), req.getPassword(), req.getName(),

				req.getBirthday(), req.getGender(), req.geteMail());

	}

	// 更改病患資訊---> ID .password 判別.name eMail更改
	@PostMapping(value = "/api/updatePatientInfo")
	public AppointmentSystemRes updatePatientInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.updatePatientInfo(req.getId(), req.getPassword(), req.getName(),
				req.geteMail());

	}

	// 更改病患資訊---> ID . password判別 --> password 更改
	@PostMapping(value = "/api/updatePatientPassword")
	public AppointmentSystemRes updatePatientPassword(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.updatePatientPassword(req.getId(), req.getPassword(), req.getNewPassword());
	}

	// 刪除病患資訊---> ID . password判別 --> 刪除
	@PostMapping(value = "/api/deletePatientPassword")
	public AppointmentSystemRes deletePatientPassword(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.deletePatientPassword(req.getId(), req.getPassword());

	}

	// 查詢病患基本訊息(不包含就診紀錄 跟 預約紀錄)
	@PostMapping(value = "/api/searchById")
	public AppointmentSystemRes searchById(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.searchById(req.getId(), req.getPassword());

	}
}