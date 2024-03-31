package com.bagas.hospital_rest.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.UserController;
import com.bagas.hospital_rest.controller.UserInfoController;
import com.bagas.hospital_rest.entity.UserInfoEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = {"id", "fio", "number"})
public class UserInfo extends RepresentationModel<UserInfo>{
	
	private long id;
	
	private String fio;
	
	private String number;
	
	public static UserInfo toModel(UserInfoEntity entity) {
		Long userInfoId = entity.getId();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userInfoId);
		userInfo.setFio(entity.getFio());
		userInfo.setNumber(entity.getNumber());
		
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserInfoController.class)
				.getOneUserInfo(userInfoId)).withSelfRel();
		
		if(entity.getUser() != null) {
			Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
					.getOneUser(entity.getUser().getId())).withRel("user");
			userInfo.add(selfLink, userLink);
		} else {
			userInfo.add(selfLink);
		}
	
		return userInfo;
	}
}
