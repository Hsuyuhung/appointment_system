package com.example.appointment_system.ifs;

import com.example.appointment_system.vo.AppointmentSystemRes;

public interface AppointmentSystemService {

	// �إ߯f�w��T
	public AppointmentSystemRes createPatientInfo(String id, String password, String name, String birthday, String gender, String eMail);

	// ���f�w��T---> ID  .password �P�O.name eMail���
	public AppointmentSystemRes updatePatientInfo(String id, String password, String name, String eMail);

	// ���f�w��T---> ID . password�P�O --> password ���
	public AppointmentSystemRes updatePatientPassword(String id, String password,String newPassword);

	// �R���f�w��T---> ID . password�P�O --> �R��
	public AppointmentSystemRes deletePatientPassword(String id, String password);
	
	//�d�߯f�w�򥻰T��(���]�t�N�E���� �� �w������)
	public AppointmentSystemRes searchById (String id, String password);
	
	
	
}
