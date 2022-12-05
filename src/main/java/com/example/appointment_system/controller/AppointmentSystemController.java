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

	// �إ߯f�w��T
	@PostMapping(value = "/api/createPatientInfo")
	public AppointmentSystemRes createPatientInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.createPatientInfo(req.getId(), req.getPassword(), req.getName(),

				req.getBirthday(), req.getGender(), req.geteMail());

	}

	// ���f�w��T---> ID .password �P�O.name eMail���
	@PostMapping(value = "/api/updatePatientInfo")
	public AppointmentSystemRes updatePatientInfo(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.updatePatientInfo(req.getId(), req.getPassword(), req.getName(),
				req.geteMail());

	}

	// ���f�w��T---> ID . password�P�O --> password ���
	@PostMapping(value = "/api/updatePatientPassword")
	public AppointmentSystemRes updatePatientPassword(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.updatePatientPassword(req.getId(), req.getPassword(), req.getNewPassword());
	}

	// �R���f�w��T---> ID . password�P�O --> �R��
	@PostMapping(value = "/api/deletePatientPassword")
	public AppointmentSystemRes deletePatientPassword(@RequestBody AppointmentSystemReq req) {

		return appointmentSystemService.deletePatientPassword(req.getId(), req.getPassword());

	}

	// �d�߯f�w�򥻰T��(���]�t�N�E���� �� �w������)
	@PostMapping(value = "/api/searchById")
	public AppointmentSystemRes searchById(@RequestBody AppointmentSystemReq req) {
		return appointmentSystemService.searchById(req.getId(), req.getPassword());

	}

}
