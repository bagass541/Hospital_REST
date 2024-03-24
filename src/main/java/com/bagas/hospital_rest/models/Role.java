package com.bagas.hospital_rest.models;

import com.bagas.hospital_rest.entity.RoleEntity;

import lombok.Data;

@Data
public class Role {

	private long id;
	
	private String authority;
	
	public static Role toModel(RoleEntity entity) {
		Role role = new Role();
		role.setId(entity.getId());
		role.setAuthority(entity.getAuthority());
		
		return role;
	}
}