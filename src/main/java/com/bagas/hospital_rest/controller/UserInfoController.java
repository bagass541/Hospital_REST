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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bagas.hospital_rest.entity.UserInfoEntity;
import com.bagas.hospital_rest.exceptions.UserInfoNotFoundException;
import com.bagas.hospital_rest.exceptions.UserInfoWithNumberExistsException;
import com.bagas.hospital_rest.models.UserInfo;
import com.bagas.hospital_rest.services.UserInfoService;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/userInfos")
	public ResponseEntity<List<UserInfo>> getUserInfos() {
		List<UserInfo> userInfos = userInfoService.getAll();
		return ResponseEntity.ok(userInfos);
	}
	
	@GetMapping("/users/{id}/userInfo")
	public ResponseEntity<UserInfo> getOneUserInfo(@PathVariable("id") Long id) {
		try {
			UserInfo userInfo = userInfoService.getOne(id);		
			return ResponseEntity.ok(userInfo);
		} catch (UserInfoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Информация о пользователе не найдена", e);
		}
	}
	
	@PostMapping("/userInfos")
	public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfoEntity userInfoEntity) {
		try {
			return ResponseEntity.ok(userInfoService.create(userInfoEntity));
		} catch (UserInfoWithNumberExistsException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Номер телефона занят", e);
		}
	}
	
	@DeleteMapping("/userInfos/{id}")
	public ResponseEntity<Long> deleteUserInfo(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.ok(userInfoService.delete(id));
		} catch (UserInfoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Информация о пользователе не найдена", e);
		}
	}
	
	@PutMapping("/userInfos/{id}")
	public ResponseEntity<UserInfo> updateUserInfo(@PathVariable("id") Long id, @RequestBody UserInfoEntity userInfoEntity) {
		try {
			return ResponseEntity.ok(userInfoService.update(id, userInfoEntity));
		}  catch (UserInfoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Информация о пользователе не найдена", e);
		}
	}
}
