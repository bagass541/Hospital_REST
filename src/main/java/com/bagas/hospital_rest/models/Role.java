package com.bagas.hospital_rest.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.RoleContoller;
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
		Long roleId = entity.getId();
		Role role = new Role();
		role.setId(roleId);
		role.setAuthority(entity.getAuthority());
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoleContoller.class)
				.getOneRole(roleId)).withSelfRel();
		
		Link putLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoleContoller.class)
				.updateRole(roleId, entity)).withRel("update").withType("PUT");
		
		Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoleContoller.class)
				.deleteRole(roleId)).withRel("delete").withType("DELETE");
		
		Link users = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getUsers()).withRel("users");
		
		role.add(selfLink, putLink, deleteLink, users);
		
		return role;
	}
}
