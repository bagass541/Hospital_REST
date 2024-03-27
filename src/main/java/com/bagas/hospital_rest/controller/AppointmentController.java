package com.bagas.hospital_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable("id") Long doctorId) {
		List<Appointment> doctorAppointments = appointmentService.getAllByDoctor(doctorId);
		return ResponseEntity.ok(doctorAppointments);
	}
}
