package com.bagas.hospital_rest.models;

import org.springframework.hateoas.RepresentationModel;

import com.bagas.hospital_rest.entity.RoleEntity;

import lombok.Data;

@Data
public class Role extends RepresentationModel<Role>{

	private long id;
	
	private String authority;
	
	public static Role toModel(RoleEntity entity) {
		Role role = new Role();
		role.setId(entity.getId());
		role.setAuthority(entity.getAuthority());
		
		return role;
	}
}
