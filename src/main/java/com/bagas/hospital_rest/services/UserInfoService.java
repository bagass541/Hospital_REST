package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.UserInfoEntity;
import com.bagas.hospital_rest.exceptions.UserInfoNotFoundException;
import com.bagas.hospital_rest.exceptions.UserInfoWithNumberExistsException;
import com.bagas.hospital_rest.models.UserInfo;
import com.bagas.hospital_rest.repositories.UserInfoRepo;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepo userInfoRepo;
	
	public List<UserInfo> getAll() {
		return userInfoRepo.findAll().stream().map(UserInfo::toModel).collect(Collectors.toList());
	}
	
	public UserInfo getOne(Long id) throws UserInfoNotFoundException {
		UserInfoEntity userInfo = userInfoRepo.findByUserEntityId(id)
				.orElseThrow(() -> new UserInfoNotFoundException());
		
		return UserInfo.toModel(userInfo);
	}
	
	public UserInfo create(UserInfoEntity userInfoEntity) throws UserInfoWithNumberExistsException {
		if(userInfoRepo.findByNumber(userInfoEntity.getNumber()) != null) throw new UserInfoWithNumberExistsException();
		
		return UserInfo.toModel(userInfoRepo.save(userInfoEntity));
	}
	
	public UserInfo update(Long id, UserInfoEntity userInfoEntity) throws UserInfoNotFoundException {
		UserInfoEntity updatinUserInfo = userInfoRepo.findById(id)
			.orElseThrow(() -> new UserInfoNotFoundException());
		
		if(userInfoEntity.getFio() != null) updatinUserInfo.setFio(userInfoEntity.getFio());
		if(userInfoEntity.getNumber() != null) updatinUserInfo.setNumber(userInfoEntity.getNumber());
		
		userInfoRepo.save(updatinUserInfo);
		return UserInfo.toModel(updatinUserInfo);
	}
	
	public Long delete(Long id) throws UserInfoNotFoundException {
		userInfoRepo.findById(id)
			.orElseThrow(() -> new UserInfoNotFoundException());
		
		userInfoRepo.deleteById(id);
		return id;
	}
}
