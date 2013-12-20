package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.Task;

public interface UserTaskService {
	
	Collection<Task> findAll(int userId);
	
	void addTask(int userId, Task task);
	
	void clearTasks(int userId);

}
