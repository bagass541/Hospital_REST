package com.bagas.hospital_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.bagas.hospital_rest.entity.UserEntity;
import com.bagas.hospital_rest.exceptions.UserNotFoundException;
import com.bagas.hospital_rest.exceptions.UsernameExistsException;
import com.bagas.hospital_rest.models.User;
import com.bagas.hospital_rest.services.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> users= userService.getAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserEntity user, UriComponentsBuilder builder) {
		try {
			User newUser = userService.create(user);
			
			return ResponseEntity.created(builder.path("/users/{id}")
					.buildAndExpand(newUser.getId())
					.toUri()).body(newUser);
		} catch (UsernameExistsException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This username exists", e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable("id") Long id) {
		try {
			User user = userService.getOne(id);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден", e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return ResponseEntity.ok(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserEntity userEntity) {
		try {
			User updatedUser = userService.update(id, userEntity);
			return ResponseEntity.ok(updatedUser);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
		}
	}
	
}
