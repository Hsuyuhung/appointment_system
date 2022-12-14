package com.example.appointment_system.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointment_system.entity.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findAllByPatientId(String patientId);
	
	public List<Appointment> findAllByDoctorId(String doctorId);

	public List<Appointment> findByDoctorIdAndAppointmentTimeAndWeek(String doctorId, String appointmentTime,
			String week);
}
