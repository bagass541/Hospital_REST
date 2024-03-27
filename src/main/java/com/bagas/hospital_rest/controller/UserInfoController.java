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
	public ResponseEntity<List<UserInfo>> getUserInfos() {
		List<UserInfo> userInfos = userInfoService.getAll();
		return ResponseEntity.ok(userInfos);
	}
	
	@GetMapping("/{id}/userInfo")
	public ResponseEntity<UserInfo> getOneUserInfo(@PathVariable("id") Long id) {
		try {
			UserInfo userInfo = userInfoService.getOne(id);		
			return ResponseEntity.ok(userInfo);
		} catch (UserInfoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Информация о пользователе не найдена", e);
		}
	}
}
