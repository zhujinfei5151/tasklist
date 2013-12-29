package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.User;

public interface UserService {

	User getUser(int userId);
	
	void addUser(User user);
	
	void removeUser(int userId);

	void updateUser(User user);

	// TODO: test
	void clearUsers();
	
	// TODO: test
	Collection<User> findAll();

	// TODO: test
	User getUserByUsername(String username);
	
	// TODO: test
	boolean loginUser(String username, String passwordHash);
	
}
