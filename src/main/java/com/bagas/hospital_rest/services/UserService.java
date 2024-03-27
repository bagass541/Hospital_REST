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
	
	public User create(UserEntity user) throws UsernameExistsException {
		if(userRepo.findByUsername(user.getUsername()) != null) throw new UsernameExistsException();
		userInfoRepo.save(user.getUserInfoEntity());
		return User.toModel(userRepo.save(user));
	}
	
	public User getOne(Long id) throws UserNotFoundException {
		UserEntity userEntity = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException());
		
		return User.toModel(userEntity);
	}
	
	public Long delete(Long id) {
		userRepo.deleteById(id);
		return id;
	}
	
	public User update(Long userId, UserEntity user) throws UserNotFoundException {
		UserEntity updatingUser = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException());
		updatingUser.setId(user.getId());
		updatingUser.setUsername(user.getUsername());
		updatingUser.setPassword(user.getPassword());
		updatingUser.setUserInfoEntity(user.getUserInfoEntity());
		
		if(user.getAuthorities() != null) {
			updatingUser.setAuthorities(user.getAuthorities());
		}
		
		if(user.getAppointments() != null) {
			updatingUser.setAppointments(user.getAppointments());
		}

		userRepo.save(updatingUser);
		return User.toModel(updatingUser);
	}
}
