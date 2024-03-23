package com.bagas.hospital_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.exceptions.DoctorNotFoundException;
import com.bagas.hospital_rest.models.Doctor;
import com.bagas.hospital_rest.services.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@PostMapping
	public ResponseEntity<String> createDoctor(@RequestBody DoctorEntity doctor) {
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getOneDoctor(@PathVariable("id") Long id) throws DoctorNotFoundException {
		System.out.println(id);
		return ResponseEntity.ok(doctorService.getOne(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteDoctor(@PathVariable("id") Long id) {
		doctorService.delete(id);
		return ResponseEntity.ok(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> changeDoctor(@PathVariable("id") Long id, @RequestBody DoctorEntity doctor) throws DoctorNotFoundException {
		return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
	}
}
