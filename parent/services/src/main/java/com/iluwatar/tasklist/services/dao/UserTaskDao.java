package com.iluwatar.tasklist.services.dao;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.Task;

public interface UserTaskDao {
	
	public Collection<Task> findAll(int userId);
	
	public void addTask(int userId, Task task);
	
	public void clearTasks(int userId);

}
