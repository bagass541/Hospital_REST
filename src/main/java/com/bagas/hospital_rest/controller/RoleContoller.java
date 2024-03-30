package com.bagas.hospital_rest.controller;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.bagas.hospital_rest.entity.RoleEntity;
import com.bagas.hospital_rest.exceptions.RoleExistsException;
import com.bagas.hospital_rest.models.Role;
import com.bagas.hospital_rest.services.RoleService;

@Controller
public class RoleContoller {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getAllRoles() {
		return ResponseEntity.ok(roleService.getAll());
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<Role> getOneRole(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(roleService.getOne(id));
		} catch (RoleNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Роль не найдена", e);
		}
	}
	
	@GetMapping("/users/{id}/roles")
	public ResponseEntity<List<Role>> getRolesByUser(@PathVariable("id") Long userId) {
		List<Role> roles = roleService.getAllByUser(userId);
		return ResponseEntity.ok(roles);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role> createRole(@RequestBody RoleEntity roleEntity, UriComponentsBuilder builder) {
		try {
			Role role = roleService.create(roleEntity);
			
			return ResponseEntity.created(builder.path("/roles/{id}").buildAndExpand(role.getId())
					.toUri()).body(role);
		} catch (RoleExistsException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Роль с таким названием уже существует", e);
		}
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<Long> deleteRole(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(roleService.delete(id));
		} catch (RoleNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Роль не найдена", e);
		}
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody RoleEntity roleEntity) {
		try {
			return ResponseEntity.ok(roleService.update(id, roleEntity));
		} catch (RoleNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Роль не найдена", e);
		}
	}
	
}
