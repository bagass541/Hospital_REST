package com.bagas.hospital_rest.models;

import com.bagas.hospital_rest.entity.UserInfoEntity;

import lombok.Data;

@Data
public class UserInfo {
	
	private long id;
	
	private String fio;
	
	private String number;
	
	public static UserInfo toModel(UserInfoEntity entity) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(entity.getId());
		userInfo.setFio(entity.getFio());
		userInfo.setNumber(entity.getNumber());
		
		return userInfo;
	}

}
