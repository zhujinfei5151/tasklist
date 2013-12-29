package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.dao.TaskDao;
import com.iluwatar.tasklist.services.entity.Tasklist;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Resource
	TaskDao tasklistDao;
	
	@Transactional
	public Collection<Tasklist> getUserTasklists(int userId) {
		return tasklistDao.getUserTasklists(userId);
	}

	@Transactional
	public Tasklist getTasklist(int tasklistId) {
		return tasklistDao.getTasklist(tasklistId);
	}

	@Transactional
	public void addTasklist(int userId, Tasklist tasklist) {
		tasklistDao.addTasklist(userId, tasklist);
	}

	@Transactional
	public void removeTasklist(int tasklistId) {
		tasklistDao.removeTasklist(tasklistId);
	}

}
