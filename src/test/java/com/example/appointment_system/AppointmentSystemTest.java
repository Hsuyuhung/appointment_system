package com.example.appointment_system;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.respository.DoctorDao;

@SpringBootTest(classes = AppointmentSystemApplication.class)
public class AppointmentSystemTest {

	@Autowired
	private DoctorDao doctorDao;
	
	@Test
	public void findByDoctorDepartmentTest() {
		List<Doctor> findByDoctorDepartmentList = doctorDao.findByDoctorDepartment("內科");
		for(Doctor doctorList : findByDoctorDepartmentList) {
			System.out.printf("醫生ID:%s 醫生姓名:%s 醫生科別:%s 醫院ID:%s 看診時段:%s 星期:%s\n"
					, doctorList.getDoctorId(), doctorList.getDoctorName(), doctorList.getDoctorDepartment(), 
					doctorList.getHospitalId(), doctorList.getAppointmentTime(), doctorList.getWeek());
		}
		
	}
}
