package com.bagas.hospital_rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bagas.hospital_rest.models.DoctorAppointment;
import com.bagas.hospital_rest.models.Appointment;
import com.bagas.hospital_rest.services.AppointmentService;

@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("users/{id}/appointments")
	public ResponseEntity<List<Appointment>> getAppointmentsForUser(@PathVariable("id") Long userId) {
		List<Appointment> userAppointments = appointmentService.getAllByUser(userId);
		return ResponseEntity.ok(userAppointments);
	}
	
	@GetMapping("/doctors/{id}/appointments")
	public ResponseEntity<List<EntityModel<DoctorAppointment>>> getAppointmentsForDoctor(@PathVariable("id") Long doctorId) {
		List<EntityModel<DoctorAppointment>> doctorAppointments = appointmentService.getAllByDoctor(doctorId).stream()
				.map(appointment -> {
					EntityModel<DoctorAppointment> model = EntityModel.of(appointment);
					return model;
				}).collect(Collectors.toList());
		return ResponseEntity.ok(doctorAppointments);
	}
}
