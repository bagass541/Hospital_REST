package com.bagas.hospital_rest.models;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.AppointmentController;
import com.bagas.hospital_rest.controller.DoctorController;
import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.entity.DoctorType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Doctor extends RepresentationModel<Doctor> {
	
	private long id;
	
	private DoctorType doctorType;
	
	private String fio;
	
	private LocalTime startWork;
	
	private LocalTime endWork;
	
	@JsonIgnore
	private List<DoctorAppointment> appointments;
	
	public static Doctor toModel(DoctorEntity entity)   {
		if(entity == null) {
			return null;
		}
		
		Doctor doctor = new Doctor();
		doctor.setId(entity.getId());
		doctor.setDoctorType(entity.getDoctorType());
		doctor.setFio(entity.getFio());
		doctor.setStartWork(entity.getStartWork());
		doctor.setEndWork(entity.getEndWork());
		doctor.setAppointments(entity.getAppointments().stream()
				.map(DoctorAppointment::toModel).collect(Collectors.toList()));
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoctorController.class)
				.getOneDoctor(doctor.getId())).withSelfRel();
		Link appointments = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AppointmentController.class)
				.getAppointmentsForDoctor(doctor.getId())).withRel("appointments");
				
		doctor.add(selfLink, appointments);
		
		return doctor;
	}
}
