package com.bagas.hospital_rest.models;

import java.time.LocalDateTime;

import com.bagas.hospital_rest.entity.AppointmentEntity;

import lombok.Data;

@Data
public class UserAppointment {

	private long id;
	
	private String doctorFio;
	
	private LocalDateTime time;
	
	public static UserAppointment toModel(AppointmentEntity entity) {
		UserAppointment userAppointment = new UserAppointment();
		userAppointment.setId(entity.getId());
		userAppointment.setDoctorFio(entity.getDoctor().getFio());
		userAppointment.setTime(entity.getTime());
		
		return userAppointment;
	}
	
}
