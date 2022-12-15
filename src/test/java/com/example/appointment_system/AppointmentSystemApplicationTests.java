package com.example.appointment_system;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.respository.DoctorDao;

@SpringBootTest
class AppointmentSystemApplicationTests {
	
	@Autowired
	DoctorDao doctorDao;

	
}
