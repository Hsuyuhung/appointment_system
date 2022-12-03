package com.example.appointment_system.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.appointment_system.entity.Hospital;
import com.example.appointment_system.entity.HospitalId;

@Repository
public interface HospitalDao extends JpaRepository<Hospital, HospitalId> {
	List<Hospital> findByHospitalId(String hospitalId);
	List<Hospital> deleteByHospitalId(String hospitalId);
}
