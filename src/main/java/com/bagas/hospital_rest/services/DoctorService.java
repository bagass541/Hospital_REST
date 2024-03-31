package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.DoctorEntity;
import com.bagas.hospital_rest.exceptions.DoctorNotFoundException;
import com.bagas.hospital_rest.models.Doctor;
import com.bagas.hospital_rest.repositories.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepo doctorRepo;
	
	public List<Doctor> getAll() {
		return doctorRepo.findAll().stream().map(Doctor::toModel).collect(Collectors.toList());
	}
	
	public Doctor getOne(Long id)  {
		DoctorEntity doctor = doctorRepo.findById(id).get();

		return Doctor.toModel(doctor);
	}
	
	public DoctorEntity createDoctor(DoctorEntity doctor) {
		return doctorRepo.save(doctor);
	}
	
	public Doctor updateDoctor(Long id, DoctorEntity doctor) throws DoctorNotFoundException {
		DoctorEntity updatingDoctor = doctorRepo.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException());
		
		updatingDoctor.setId(id);
		updatingDoctor.setFio(doctor.getFio());
		updatingDoctor.setDoctorType(doctor.getDoctorType());
		updatingDoctor.setStartWork(doctor.getStartWork());
		updatingDoctor.setEndWork(doctor.getEndWork());
		
		doctorRepo.save(updatingDoctor);
		
		return Doctor.toModel(updatingDoctor);
	}
	
	public Long delete(Long id) throws DoctorNotFoundException {
		doctorRepo.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException());
		
		doctorRepo.deleteById(id);
		return id;
	}
}
