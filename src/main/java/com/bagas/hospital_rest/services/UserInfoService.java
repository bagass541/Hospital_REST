package com.bagas.hospital_rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.UserInfoEntity;
import com.bagas.hospital_rest.exceptions.UserInfoNotFoundException;
import com.bagas.hospital_rest.models.UserInfo;
import com.bagas.hospital_rest.repositories.UserInfoRepo;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepo userInfoRepo;
	
	public List<UserInfoEntity> getAll() {
		return userInfoRepo.findAll();
	}
	
	public UserInfo getOne(Long id) throws UserInfoNotFoundException {
		UserInfoEntity userInfo = userInfoRepo.findByUserEntityId(id);
		
		if(userInfo == null) throw new UserInfoNotFoundException();
		
		return UserInfo.toModel(userInfo);
	}
}