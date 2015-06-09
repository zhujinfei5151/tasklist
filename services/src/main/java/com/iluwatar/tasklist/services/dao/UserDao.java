package com.iluwatar.tasklist.services.dao;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.User;

public interface UserDao {

	User getUser(int userId);
	
	void addUser(User user);
	
	void removeUser(int userId);

	void updateUser(User user);
	
	void clearUsers();

	Collection<User> findAll();

	User getUserByUsername(String username);
	
	boolean loginUser(String username, String passwordHash);
	
}
