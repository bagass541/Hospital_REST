package com.bagas.hospital_rest.models;

import java.time.LocalTime;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.AppointmentController;
import com.bagas.hospital_rest.controller.DoctorController;
import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.entity.DoctorType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Doctor extends RepresentationModel<Doctor> {
	
	private long id;
	
	private DoctorType doctorType;
	
	private String fio;
	
	private LocalTime startWork;
	
	private LocalTime endWork;
	
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
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoctorController.class)
				.getOneDoctor(doctor.getId())).withSelfRel();
		Link appointments = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AppointmentController.class)
				.getAppointmentsForDoctor(doctor.getId())).withRel("appointments");
				
		doctor.add(selfLink, appointments);
		
		return doctor;
	}
}
