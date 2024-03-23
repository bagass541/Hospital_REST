package com.bagas.hospital_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.models.Doctor;
import com.bagas.hospital_rest.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@PostMapping
	public ResponseEntity<String> createDoctor(DoctorEntity doctor) {
		DoctorEntity newDoctor = doctorService.createDoctor(doctor);
		
		if(newDoctor != null) {
			return ResponseEntity.ok("Врач успешно добавлен");
		} else {
			return ResponseEntity.ok("Произошла ошибка");
		}

	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getDoctors() {
		List<Doctor> doctors = doctorService.getAll();
		
		return ResponseEntity.ok(doctors);
	}
}
