package com.bagas.hospital_rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bagas.hospital_rest.models.Role;
import com.bagas.hospital_rest.services.RoleService;

@Controller
public class RoleContoller {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users/{id}/roles")
	public ResponseEntity<List<EntityModel<Role>>> getRolesByUser(@PathVariable("id") Long userId) {
		List<EntityModel<Role>> roles = roleService.getAllByUser(userId).stream()
				.map(role -> {
					EntityModel<Role> model = EntityModel.of(role);
					return model;
				}).collect(Collectors.toList());
		
		return ResponseEntity.ok(roles);
	}
}
