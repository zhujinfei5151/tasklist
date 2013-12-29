package com.iluwatar.tasklist.services.dao;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.Tasklist;

public interface TaskDao {

	Collection<Tasklist> getUserTasklists(int userId);
	
	Tasklist getTasklist(int tasklistId);
	
	void addTasklist(int userId, Tasklist tasklist);

	void removeTasklist(int tasklistId);
}
