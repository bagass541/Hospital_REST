package com.bagas.hospital_rest.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DoctorType {
	SURGEON("Хирург"),
	THERAPIST("Терапевт"),
	ENT("Лор"),
	OPHTHAMLMOLOGITS("Окулист"),
	DERMATOLOGIST("Дерматолог"),
	OPRTHOPEDIST("Ортопед"),
	NEUROLOGIST("Невропатолог"),
	CARDIOLOGIST("Кардиолог"),
	PSYCHIATRIST("Психиатр");
	
	private final String translate;

	/**
	 * Получить enum-type тип доктора по его переводу.
	 * 
	 * @param translate Перевод наименования типа доктора.
	 * @return Тип доктора, соответствующий переданному переводу, или null, если такого типа нет.
	 */
	public static DoctorType getByTranslate(String translate) {
		
		for(DoctorType type : values()) {
			if(type.getTranslate().equals(translate)) {
				return type;
			}
		}
		return null;
	}
}
