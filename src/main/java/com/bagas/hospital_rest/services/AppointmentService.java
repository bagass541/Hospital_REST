package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.AppointmentEntity;
import com.bagas.hospital_rest.exceptions.AppointmentNotFoundException;
import com.bagas.hospital_rest.models.Appointment;
import com.bagas.hospital_rest.repositories.AppointmentRepo;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepo appointmentRepo;
	
	public List<Appointment> getAll() {
		return appointmentRepo.findAll().stream().map(Appointment::toModel).collect(Collectors.toList());
	}
	
	public List<Appointment> getAllByUser(Long userId) {
		List<Appointment> userAppointments =  appointmentRepo.findByUserId(userId).stream()
				.map(Appointment::toModel)
				.collect(Collectors.toList());
		
		return userAppointments;
	}
	
	public List<Appointment> getAllByDoctor(Long doctorId) {
		List<Appointment> doctorAppointments = appointmentRepo.findByDoctorId(doctorId).stream()
				.map(Appointment::toModel)
				.collect(Collectors.toList());
		
		return doctorAppointments;
	}
	
	public Appointment getOne(Long id) throws AppointmentNotFoundException {
		AppointmentEntity appointment = appointmentRepo.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException());
	
		return Appointment.toModel(appointment);
	}
	
	public Appointment create(AppointmentEntity appointmentEntity) throws AppointmentNotFoundException {
		AppointmentEntity theSameAppointment = appointmentRepo.findByDoctorUserTime(
				appointmentEntity.getDoctor().getId(), 
				appointmentEntity.getUser().getId(), 
				appointmentEntity.getTime());
		
		if(theSameAppointment != null) throw new AppointmentNotFoundException(); 
		
		return Appointment.toModel(appointmentRepo.save(appointmentEntity));
	}
	
	public Long delete(Long id) throws AppointmentNotFoundException {
		if(appointmentRepo.findById(id).get() == null) throw new AppointmentNotFoundException();
		
		appointmentRepo.deleteById(id);
		return id;
	}
	
	public Appointment update(Long id, AppointmentEntity appointmentEntity) throws AppointmentNotFoundException {
		AppointmentEntity updatingAppointment = appointmentRepo.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException());
		
		if(appointmentEntity.getDoctor() != null) updatingAppointment.setDoctor(appointmentEntity.getDoctor());
		if(appointmentEntity.getUser() != null) updatingAppointment.setUser(appointmentEntity.getUser());
		if(appointmentEntity.getTime() != null) updatingAppointment.setTime(appointmentEntity.getTime());
		
		return Appointment.toModel(appointmentRepo.save(appointmentEntity));
	}
	
}
