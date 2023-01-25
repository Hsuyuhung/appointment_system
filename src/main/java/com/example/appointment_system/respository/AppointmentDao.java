package com.example.appointment_system.respository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointment_system.entity.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findAllByPatientId(String patientId);

	public List<Appointment> findAllByDoctorId(String doctorId);

	public List<Appointment> findByDoctorIdAndAppointmentTimeAndWeek(String doctorId, String appointmentTime,
			String week);

	public Optional<Appointment> findByPatientId(String patientId);

	// (12/16)新增的方法 --> 判斷這個醫生的ID當天當時段的資料抓到Optional
	public List<Appointment> findByDoctorIdAndAppointmentTimeAndAppointmentDate(String doctorId, String appointmentTime,
			LocalDate appointmentDate);
}
