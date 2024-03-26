package com.bagas.hospital_rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bagas.hospital_rest.models.UserAppointment;
import com.bagas.hospital_rest.services.AppointmentService;

@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("users/{id}/appointments")
	public ResponseEntity<List<EntityModel<UserAppointment>>> getAppointmentsForUser(@PathVariable("id") Long userId) {
		List<EntityModel<UserAppointment>> userAppointments = appointmentService.getAllByUser(userId).stream()
				.map(appointment -> {
					EntityModel<UserAppointment> model = EntityModel.of(appointment);
					return model;
				}).collect(Collectors.toList());
		return ResponseEntity.ok(userAppointments);
	}
}