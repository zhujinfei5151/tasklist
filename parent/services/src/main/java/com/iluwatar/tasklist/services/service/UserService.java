package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.User;

public interface UserService {

	void addUser(User user);
	
	void removeUser(int userId);
	
	void clearUsers();
	
	Collection<User> findAll();
	
	User getUser(int userId);
	
	boolean loginUser(String username, String passwordHash);
	
}
