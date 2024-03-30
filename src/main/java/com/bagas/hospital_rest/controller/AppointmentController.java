package com.bagas.hospital_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.bagas.hospital_rest.entity.AppointmentEntity;
import com.bagas.hospital_rest.exceptions.AppointmentNotFoundException;
import com.bagas.hospital_rest.models.Appointment;
import com.bagas.hospital_rest.services.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAppointments() {
		return ResponseEntity.ok(appointmentService.getAll());
	}
	
	@GetMapping("/users/{id}/appointments")
	public ResponseEntity<List<Appointment>> getAppointmentsForUser(@PathVariable("id") Long userId) {
		List<Appointment> userAppointments = appointmentService.getAllByUser(userId);
		return ResponseEntity.ok(userAppointments);
	}
	
	@GetMapping("/doctors/{id}/appointments")
	public ResponseEntity<List<Appointment>> getAppointmentsForDoctor(@PathVariable("id") Long doctorId) {
		List<Appointment> doctorAppointments = appointmentService.getAllByDoctor(doctorId);
		return ResponseEntity.ok(doctorAppointments);
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getOneAppointment(@PathVariable("id") Long id) {
		try {
			Appointment appointment = appointmentService.getOne(id);
			return ResponseEntity.ok(appointment);
		} catch (AppointmentNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена", e);
		}
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentEntity appointment, UriComponentsBuilder builder) {
		try {
			Appointment newAppointment = appointmentService.create(appointment);
			
			return ResponseEntity.created(builder.path("/appointments/{id}")
					.buildAndExpand(newAppointment.getId())
					.toUri()).body(newAppointment);
		} catch (AppointmentNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This appointments exists", e);
		}
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<Long> deleteAppointment(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(appointmentService.delete(id));
		} catch (AppointmentNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена", e);
		}
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable("id") Long id, @RequestBody AppointmentEntity appointment) {
		try {
			Appointment newAppointment = appointmentService.update(id, appointment);
			return ResponseEntity.ok(newAppointment);
		} catch (AppointmentNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Запись не найдена", e);
		}
	}
}
