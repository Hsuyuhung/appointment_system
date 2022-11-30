package com.example.appointment_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment_system.ifs.AppointmentSystemService;

@RestController
public class AppointmentSystemController {
	
	@Autowired
	private AppointmentSystemService appointmentSystemService;

}
