package com.bagas.hospital_rest.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.bagas.hospital_rest.controller.UserController;
import com.bagas.hospital_rest.entity.UserInfoEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends RepresentationModel<UserInfo>{
	
	private long id;
	
	private String fio;
	
	private String number;
	
	public static UserInfo toModel(UserInfoEntity entity) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(entity.getId());
		userInfo.setFio(entity.getFio());
		userInfo.setNumber(entity.getNumber());
		
		Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
				.getOneUser(entity.getUserEntity().getId())).withRel("user");
		
		userInfo.add(userLink);
		
		return userInfo;
	}

}
