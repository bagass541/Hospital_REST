package com.bagas.hospital_rest.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagas.hospital_rest.entity.RoleEntity;
import com.bagas.hospital_rest.exceptions.RoleExistsException;
import com.bagas.hospital_rest.models.Role;
import com.bagas.hospital_rest.repositories.RoleRepo;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> getAll() {
		return roleRepo.findAll().stream().map(Role::toModel).collect(Collectors.toList());
	}
	
	public List<Role> getAllByUser(Long userId) {
		return roleRepo.findAllByUserId(userId).stream().map(Role::toModel).collect(Collectors.toList());
	}
	
	public Role getOne(Long id) throws RoleNotFoundException {
		RoleEntity role = roleRepo.findById(id)
				.orElseThrow(() -> new RoleNotFoundException());
		
		return Role.toModel(role);
	}
	
	public Role create(RoleEntity roleEntity) throws RoleExistsException {
		if(roleRepo.findByAuthority(roleEntity.getAuthority()) != null) throw new RoleExistsException();
		
		return Role.toModel(roleRepo.save(roleEntity));
	}
	
	public Role update(Long id, RoleEntity roleEntity) throws RoleNotFoundException {
		RoleEntity updatingRole = roleRepo.findById(id)
				.orElseThrow(() -> new RoleNotFoundException());
		
		if(roleEntity.getAuthority() != null) updatingRole.setAuthority(roleEntity.getAuthority());
		if(roleEntity.getUsers() != null) updatingRole.setUsers(roleEntity.getUsers());
		
		return Role.toModel(roleRepo.save(updatingRole));
	}
	
	public Long delete(Long id) throws RoleNotFoundException {
		if(roleRepo.findById(id).get() == null) throw new RoleNotFoundException();
		
		roleRepo.deleteById(id);
		return id;
	}
}
