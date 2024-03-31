package com.bagas.hospital_rest.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.AppointmentController;
import com.bagas.hospital_rest.controller.DoctorController;
import com.bagas.hospital_rest.controller.UserController;
import com.bagas.hospital_rest.entity.AppointmentEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id", "time"})
public class Appointment extends RepresentationModel<Appointment> {

	private long id;
	
	private LocalDateTime time;
	
	public static Appointment toModel(AppointmentEntity entity) {
		Long appointmentId = entity.getId();
		
		Appointment userAppointment = new Appointment();
		userAppointment.setId(appointmentId);
		userAppointment.setTime(entity.getTime());
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AppointmentController.class)
				.getOneAppointment(appointmentId)).withSelfRel();
		
		Link doctorLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DoctorController.class)
				.getOneDoctor(entity.getDoctor().getId())).withRel("doctor");
		

		if(entity.getUser() != null) {
			Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(entity.getUser().getId())).withRel("user");
			userAppointment.add(selfLink, doctorLink, userLink);
		} else {
			userAppointment.add(selfLink, doctorLink);
		}
	
		return userAppointment;
	}
	
}
