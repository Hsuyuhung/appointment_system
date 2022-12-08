package com.example.appointment_system.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, String> {
      
	public List<Doctor> findByDoctorId(String doctorId) ;
	
	public List<Doctor> findByDoctorIdAndAppointmentTimeAndWeek(String DoctorId, String appointmentTime, String week) ;
}
