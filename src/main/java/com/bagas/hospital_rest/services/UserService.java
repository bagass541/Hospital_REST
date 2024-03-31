package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.UserEntity;
import com.bagas.hospital_rest.exceptions.UserNotFoundException;
import com.bagas.hospital_rest.exceptions.UsernameExistsException;
import com.bagas.hospital_rest.models.User;
import com.bagas.hospital_rest.repositories.UserInfoRepo;
import com.bagas.hospital_rest.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserInfoRepo userInfoRepo;
	
	public List<User> getAll() {
		return userRepo.findAll().stream().map(User::toModel).collect(Collectors.toList());
	}
	
	public User getOne(Long id) throws UserNotFoundException {
		UserEntity userEntity = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException());
		
		return User.toModel(userEntity);
	}
	
	public User create(UserEntity user) throws UsernameExistsException {
		if(userRepo.findByUsername(user.getUsername()) != null) throw new UsernameExistsException();
		
		if(user.getUserInfo() != null) {
			userInfoRepo.save(user.getUserInfo());
		}
		
		return User.toModel(userRepo.save(user));
	} 
	
	public User update(Long id, UserEntity user) throws UserNotFoundException {
		UserEntity updatingUser = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException());

		if(user.getUsername() != null) updatingUser.setUsername(user.getUsername());
		if(user.getPassword() != null) updatingUser.setPassword(user.getPassword());
		if(user.getUserInfo() != null) updatingUser.setUserInfo(user.getUserInfo());
		if(user.getRoles() != null) updatingUser.setRoles(user.getRoles());
		if(user.getAppointments() != null) updatingUser.setAppointments(user.getAppointments());
		
		return User.toModel(userRepo.save(updatingUser));
	}
	
	public Long delete(Long id) throws UserNotFoundException {
		if(userRepo.findById(id).get() == null) throw new UserNotFoundException();
		
		userRepo.deleteById(id);
		return id;
	}
}
