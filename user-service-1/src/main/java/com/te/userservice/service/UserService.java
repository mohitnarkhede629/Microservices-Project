package com.te.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.userservice.entity.User;
import com.te.userservice.reposirory.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	
	public User getUser(String userName) {
		return repo.findByUserName(userName).get();
	}

}
