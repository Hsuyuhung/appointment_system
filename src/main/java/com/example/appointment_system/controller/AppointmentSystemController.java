package com.example.appointment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.vo.AppointmentSystemReq;
import com.example.appointment_system.vo.AppointmentSystemRes;

@RestController
public class AppointmentSystemController {

	@Autowired
	private AppointmentSystemService appointmentSystemService;

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
