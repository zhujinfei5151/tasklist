package com.iluwatar.tasklist.services.service;

import java.util.Collection;

import com.iluwatar.tasklist.services.entity.Tasklist;

public interface TaskService {
	
	Collection<Tasklist> getUserTasklists(int userId);
	
	Tasklist getTasklist(int tasklistId);

}
