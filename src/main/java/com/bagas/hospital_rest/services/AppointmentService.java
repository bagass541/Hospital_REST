package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.models.DoctorAppointment;
import com.bagas.hospital_rest.models.Appointment;
import com.bagas.hospital_rest.repositories.AppointmentRepo;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepo;
	
	public List<Appointment> getAllByUser(Long userId) {
		List<Appointment> userAppointments =  appointmentRepo.findByUserId(userId).stream()
				.map(Appointment::toModel)
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
