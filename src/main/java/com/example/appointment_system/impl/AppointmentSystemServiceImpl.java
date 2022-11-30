package com.example.appointment_system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointment_system.ifs.AppointmentSystemService;
import com.example.appointment_system.respository.AppointmentDao;
import com.example.appointment_system.respository.DoctorDao;
import com.example.appointment_system.respository.HospitalDao;
import com.example.appointment_system.respository.PatientDao;

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
	
	

}
