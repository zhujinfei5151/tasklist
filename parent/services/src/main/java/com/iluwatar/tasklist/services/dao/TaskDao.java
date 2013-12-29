package com.iluwatar.tasklist.services.dao;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;

public interface TaskDao {

	// tasklists
	
	Collection<Tasklist> getUserTasklists(int userId);
	
	Tasklist getTasklist(int tasklistId);
	
	void addTasklist(int userId, Tasklist tasklist);

	void removeTasklist(int tasklistId);

	// tasks
	
	Collection<Task> getTasklistTasks(int tasklistId);
	
	Task getTask(int taskId);
	
	void addTask(int tasklistId, Task task);
	
	void removeTask(int taskId);

}
