package com.bagas.hospital_rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	public ResponseEntity<List<EntityModel<User>>> getUsers() {
		List<EntityModel<User>> models = userService.getAll().stream()
				.map(user -> {
					Long userId = user.getId();
					Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
							.getOneUser(userId))
							.withSelfRel();
					Link userInfoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserInfoController.class)
	                        .getOneUserInfo(userId))
	                        .withRel("userInfo");
					Link appointments = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AppointmentController.class)
							.getAppointmentsForUser(userId))
							.withRel("appointments");
					Link roles = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RoleContoller.class)
							.getRolesByUser(userId))
							.withRel("roles");
					return EntityModel.of(user, userLink, userInfoLink, appointments, roles);
				}).collect(Collectors.toList());
		
		return ResponseEntity.ok(models);
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<User>> createUser(@RequestBody UserEntity user, UriComponentsBuilder builder) {
		try {
			User newUser = userService.create(user);
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(newUser.getId()))
					.withSelfRel();
			EntityModel<User> model = EntityModel.of(newUser, selfLink);
			
			return ResponseEntity.created(builder.path("/users/{id}")
					.buildAndExpand(newUser.getId())
					.toUri()).body(model);
			
		} catch (UsernameExistsException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This username exists", e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<User>> getOneUser(@PathVariable("id") Long id) {
		try {
			User user = userService.getOne(id);
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(user.getId()))
					.withSelfRel();
			Link usersLink = WebMvcLinkBuilder.linkTo(UserController.class).withRel("users");
			
			EntityModel<User> model = EntityModel.of(user, selfLink, usersLink);
			return ResponseEntity.ok(model);
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
	public ResponseEntity<EntityModel<User>> updateUser(@PathVariable("id") Long id, @RequestBody UserEntity userEntity) {
		try {
			User updatedUser = userService.update(id, userEntity);
			Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(updatedUser.getId()))
					.withSelfRel();
			EntityModel<User> model = EntityModel.of(updatedUser, selfLink);
			return ResponseEntity.ok(model);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
		}
	}
	
}
