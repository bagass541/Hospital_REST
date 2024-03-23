package com.bagas.hospital_rest.models;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bagas.hospital_rest.entity.Role;
import com.bagas.hospital_rest.entity.UserEntity;
import com.bagas.hospital_rest.entity.UserInfoEntity;

import lombok.Data;

@Data
public class User {

	private long id;
	
	private String username;
	
	private String password;
	
	private UserInfoEntity userInfoEntity;
	
	private Set<Role> authorities;

	private List<UserAppointment> appointments;
	
	public static User toModel(UserEntity entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setUserInfoEntity(entity.getUserInfoEntity());
		user.setAuthorities(entity.getAuthorities());
		user.setAppointments(entity.getAppointments().stream().map(UserAppointment::toModel).collect(Collectors.toList()));
		
		return user;
	}
}
