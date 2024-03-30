package com.bagas.hospital_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users_info")
@Data
@EqualsAndHashCode(of = {"id", "fio", "number"})
public class UserInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String fio;
	
	private String number;
	
	@OneToOne(mappedBy = "userInfo")
	private UserEntity user;
}
