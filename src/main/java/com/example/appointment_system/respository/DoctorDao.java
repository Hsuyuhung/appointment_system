package com.example.appointment_system.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointment_system.entity.Doctor;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, String> {

	public List<Doctor> findByDoctorId(String doctorId);

	public Optional<Doctor> findByDoctorIdAndAppointmentTimeAndWeek(String doctorId, String appointmentTime, String week);
	
	public List<Doctor> findAllBydoctorIdIn(List<String> doctorIdList);
	
	public List<Doctor> findByDoctorDepartment(String doctorDepartment);
}
