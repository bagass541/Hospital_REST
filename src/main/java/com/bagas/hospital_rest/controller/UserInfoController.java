package com.bagas.hospital_rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bagas.hospital_rest.exceptions.UserInfoNotFoundException;
import com.bagas.hospital_rest.models.UserInfo;
import com.bagas.hospital_rest.services.UserInfoService;

@RequestMapping("/users")
@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/userInfos")
	public ResponseEntity<List<EntityModel<UserInfo>>> getUserInfos() {
		List<EntityModel<UserInfo>> models = userInfoService.getAll().stream()
				.map(userInfo -> {
					Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(userInfo.getUserEntity().getId()))
					.withRel("/users/" + userInfo.getUserEntity().getId());
					EntityModel<UserInfo> model = EntityModel.of(UserInfo.toModel(userInfo), userLink);
					return model;
				}).collect(Collectors.toList());
		
		return ResponseEntity.ok(models);
	}
	
	@GetMapping("/{id}/userInfo")
	public ResponseEntity<EntityModel<UserInfo>> getOneUserInfo(@PathVariable("id") Long id) {
		try {
			UserInfo userInfo = userInfoService.getOne(id);
			EntityModel<UserInfo> model = EntityModel.of(userInfo);
			
			return ResponseEntity.ok(model);
		} catch (UserInfoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Информация о пользователе не найдена", e);
		}
	}
}
