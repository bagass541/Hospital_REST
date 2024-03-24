package com.bagas.hospital_rest.models;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bagas.hospital_rest.entity.UserEntity;

import lombok.Data;

@Data
public class User {

	private long id;
	
	private String username;
	
	private String password;
	
	private UserInfo userInfo;
	
	private Set<Role> authorities;

	private List<UserAppointment> appointments;
	
	public static User toModel(UserEntity entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setUsername(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setUserInfo(UserInfo.toModel(entity.getUserInfoEntity()));
		user.setAuthorities(entity.getAuthorities().stream().map(Role::toModel).collect(Collectors.toSet()));
		user.setAppointments(entity.getAppointments().stream().map(UserAppointment::toModel).collect(Collectors.toList()));
		
		return user;
	}
}