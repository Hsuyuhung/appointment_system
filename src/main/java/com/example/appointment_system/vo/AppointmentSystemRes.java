package com.example.appointment_system.vo;

import java.util.List;

import com.example.appointment_system.entity.Appointment;
import com.example.appointment_system.entity.Doctor;
import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.Patient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentSystemRes {

	private String message;

	@JsonProperty(value = "hospital_info")
	private Hospital hospital;

	@JsonProperty(value = "hospital_list")
	private List<Hospital> hospitalList;

	@JsonProperty(value = "doctor_info")
	private Doctor doctor;

	private List<Doctor> doctorList;

	private Patient patient;

	private Appointment appointment;

	private List<Patient> patientlist;

	private List<AppoinmentInfo> appointmentInfoList;

	public AppointmentSystemRes() {

	}

	public AppointmentSystemRes(String message, Hospital hospital) {
		this.message = message;
		this.hospital = hospital;
	}

	public AppointmentSystemRes(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}

	public AppointmentSystemRes(Doctor doctor, String message) {
		this.doctor = doctor;
		this.message = message;
	}

	public AppointmentSystemRes(Patient patient, List<AppoinmentInfo> appointmentInfoList) {
		this.patient = patient;
		this.appointmentInfoList = appointmentInfoList;
	}

	public AppointmentSystemRes(String message) {
		this.message = message;
	}

	public AppointmentSystemRes(Patient patient, String message) {
		this.patient = patient;
		this.message = message;
	}

	//預約系統回傳訊息
	public AppointmentSystemRes(Appointment appointment, String message) {
		this.appointment = appointment;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Hospital> getHospitalList() {
		return hospitalList;
	}

	public void setHospitalList(List<Hospital> hospitalList) {
		this.hospitalList = hospitalList;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Doctor> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<Patient> getPatientlist() {
		return patientlist;
	}

	public void setPatientlist(List<Patient> patientlist) {
		this.patientlist = patientlist;
	}

	public List<AppoinmentInfo> getAppointmentInfoList() {
		return appointmentInfoList;
	}

	public void setAppointmentInfoList(List<AppoinmentInfo> appointmentInfoList) {
		this.appointmentInfoList = appointmentInfoList;
	}
}