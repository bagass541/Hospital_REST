package com.bagas.hospital_rest.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.UserController;
import com.bagas.hospital_rest.entity.RoleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id", "authority"})
public class Role extends RepresentationModel<Role> {

	private long id;
	
	private String authority;
	
	public static Role toModel(RoleEntity entity) {
		Role role = new Role();
		role.setId(entity.getId());
		role.setAuthority(entity.getAuthority());
		
		Link users = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getUsers()).withRel("users");
		
		role.add(users);
		
		return role;
	}
}
