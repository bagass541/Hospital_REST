package com.bagas.hospital_rest.models;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.AppointmentController;
import com.bagas.hospital_rest.controller.RoleContoller;
import com.bagas.hospital_rest.controller.UserController;
import com.bagas.hospital_rest.controller.UserInfoController;
import com.bagas.hospital_rest.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id", "username", "userInfo"})
public class User extends RepresentationModel<User> {

	private long id;
	
	private String username;
	
	@JsonIgnore
	private UserInfo userInfo;
	
	@JsonIgnore
	private Set<Role> authorities;

	@JsonIgnore
	private List<Appointment> appointments;
	
	public static User toModel(UserEntity entity) {
		User user = new User();
		Long userId = entity.getId();
		user.setId(userId);
		user.setUsername(entity.getUsername());
		
		if(entity.getRoles() != null) user.setAuthorities(entity.getRoles().stream().map(Role::toModel).collect(Collectors.toSet()));
		
		if(entity.getUserInfo() != null) {
			user.setUserInfo(UserInfo.toModel(entity.getUserInfo()));
		}
		
		if(entity.getAppointments() != null) {
			user.setAppointments(entity.getAppointments().stream().map(Appointment::toModel).collect(Collectors.toList()));
		}
		
		Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getOneUser(userId))
				.withSelfRel();
		Link userInfoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserInfoController.class)
                .getOneUserInfo(userId))
                .withRel("userInfo");
		Link appointmentsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AppointmentController.class)
				.getAppointmentsForUser(userId))
				.withRel("appointments");
		Link rolesLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoleContoller.class)
				.getRolesByUser(userId))
				.withRel("roles");
		
		user.add(userLink, userInfoLink, appointmentsLink, rolesLink);
		return user;
	}
	
	
}
