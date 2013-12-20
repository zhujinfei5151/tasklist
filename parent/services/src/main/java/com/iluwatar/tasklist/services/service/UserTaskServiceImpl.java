package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.dao.UserTaskDao;
import com.iluwatar.tasklist.services.entity.Task;

@Service("userTaskService")
public class UserTaskServiceImpl implements UserTaskService {

	@Resource
	UserTaskDao userTaskDao;
	
	@Transactional
	public Collection<Task> findAll(int userId) {
		return userTaskDao.findAll(userId);
	}

	@Transactional
	public void addTask(int userId, Task task) {
		userTaskDao.addTask(userId, task);
	}

	@Transactional
	public void clearTasks(int userId) {
		userTaskDao.clearTasks(userId);
	}

}
