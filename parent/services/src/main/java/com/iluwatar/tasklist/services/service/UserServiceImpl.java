package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.dao.UserDao;
import com.iluwatar.tasklist.services.entity.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Transactional
	public void removeUser(int userId) {
		userDao.removeUser(userId);
	}

	@Transactional
	public void clearUsers() {
		userDao.clearUsers();
	}

	@Transactional
	public Collection<User> findAll() {
		return userDao.findAll();
	}

	@Transactional
	public User getUser(int userId) {
		return userDao.getUser(userId);
	}

	@Transactional
	public boolean loginUser(String username, String passwordHash) {
		return userDao.loginUser(username, passwordHash);
	}

	@Transactional
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

}
