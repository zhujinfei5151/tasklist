package com.iluwatar.tasklist.services.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.dao.TaskDao;
import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Resource
	TaskDao taskDao;
	
	@Transactional
	public Collection<Tasklist> getUserTasklists(int userId) {
		return taskDao.getUserTasklists(userId);
	}

	@Transactional
	public Tasklist getTasklist(int tasklistId) {
		return taskDao.getTasklist(tasklistId);
	}

	@Transactional
	public void addTasklist(int userId, Tasklist tasklist) {
		taskDao.addTasklist(userId, tasklist);
	}

	@Transactional
	public void removeTasklist(int tasklistId) {
		taskDao.removeTasklist(tasklistId);
	}

	@Transactional
	public Task getTask(int taskId) {
		return taskDao.getTask(taskId);
	}

	@Transactional
	public void addTask(int tasklistId, Task task) {
		taskDao.addTask(tasklistId, task);
	}

	@Transactional
	public void removeTask(int taskId) {
		taskDao.removeTask(taskId);
	}

	@Transactional
	public Collection<Task> getTasklistTasks(int tasklistId) {
		return taskDao.getTasklistTasks(tasklistId);
	}

	@Transactional
	public Collection<Task> getTasklistTasksNotCompleted(int tasklistId) {
		Collection<Task> allTasks = taskDao.getTasklistTasks(tasklistId);
		Collection<Task> filteredTasks = filterTasksByCompleted(allTasks, false);
		return filteredTasks;
	}
	
	@Transactional
	public void updateTasklist(Tasklist tasklist) {
		taskDao.updateTasklist(tasklist);
	}

	@Transactional
	public void updateTask(Task task) {
		taskDao.updateTask(task);
	}

	@Override
	public Collection<Task> getTasklistTasksCompleted(int tasklistId) {
		return taskDao.getTasklistTasksCompleted(tasklistId);
	}

	private Collection<Task> filterTasksByCompleted(Collection<Task> allTasks, boolean completed) {
		Collection<Task> filteredTasks = new ArrayList<>();
		for (Task t: allTasks) {
			if (t.isDone() == completed) {
				filteredTasks.add(t);
			}
		}
		return filteredTasks;
	}
}
