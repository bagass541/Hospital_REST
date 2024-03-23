package com.bagas.hospital_rest.models;

import java.time.LocalDateTime;

import com.bagas.hospital_rest.entity.AppointmentEntity;

import lombok.Data;

@Data
public class DoctorAppointment {

	private long id;
	
	private String username;
	
	private LocalDateTime time;
	
	public static DoctorAppointment toModel(AppointmentEntity appointmentEntity) {
		DoctorAppointment doctorAppointment = new DoctorAppointment();
		doctorAppointment.setId(appointmentEntity.getId());
		if(appointmentEntity.getUser() != null) {
			doctorAppointment.setUsername(appointmentEntity.getUser().getUserInfoEntity().getFio());
		}
		doctorAppointment.setTime(appointmentEntity.getTime());
		
		return doctorAppointment;
	}
}
