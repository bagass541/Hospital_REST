package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.models.DoctorAppointment;
import com.bagas.hospital_rest.models.UserAppointment;
import com.bagas.hospital_rest.repositories.AppointmentRepo;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepo;
	
	public List<UserAppointment> getAllByUser(Long userId) {
		List<UserAppointment> userAppointments =  appointmentRepo.findByUserId(userId).stream()
				.map(UserAppointment::toModel)
				.collect(Collectors.toList());
		
		return userAppointments;
	}
	
	public List<DoctorAppointment> getAllByDoctor(Long doctorId) {
		List<DoctorAppointment> doctorAppointments = appointmentRepo.findByDoctorId(doctorId).stream()
				.map(DoctorAppointment::toModel)
				.collect(Collectors.toList());
		
		return doctorAppointments;
	}
}
