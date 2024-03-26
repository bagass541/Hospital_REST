package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.models.Role;
import com.bagas.hospital_rest.repositories.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> getAllByUser(Long userId) {
		return roleRepo.findAllByUserId(userId).stream().map(Role::toModel).collect(Collectors.toList());
	}
}
