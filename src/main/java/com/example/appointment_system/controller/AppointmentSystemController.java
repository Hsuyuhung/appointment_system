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

		if (!StringUtils.hasText(req.getDepartment())) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_DEPARTMENT_EMPTY.getMessage());
		}

//		醫院代碼的"^[A-Z]+$""^[0-9]+(.[0-9]{3})?$" 電話的"^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$"

		Hospital hospital = appointmentSystemService.createHospitalInfo(req.getHospitalId(), req.getHospitalName(),
				req.getPhone(), req.getCity(), req.getDistrict(), req.getAddress(), req.getDepartment());
		if (hospital == null) {
			return new AppointmentSystemRes(AppointmentSystemRtnCode.HOSPITAL_ID_EXSITED.getMessage());
		}
		return new AppointmentSystemRes(AppointmentSystemRtnCode.SUCCESSFUL.getMessage(), hospital);

	}

}
