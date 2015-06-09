package com.iluwatar.tasklist.services.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.entity.User;

@Repository("userTasklistDao")
public class TaskDaoImpl implements TaskDao {
	
	@PersistenceContext
	private EntityManager em;

	public Collection<Tasklist> getUserTasklists(int userId) {
		Query q = em.createQuery("select t from Tasklist t where t.user.id=:userId");
		q.setParameter("userId", userId);
		List<Tasklist> tasklists = q.getResultList();
		return tasklists;
	}

	public Tasklist getTasklist(int tasklistId) {
		Tasklist tasklist = em.find(Tasklist.class, tasklistId);
		return tasklist;
	}

	public void addTasklist(int userId, Tasklist tasklist) {
		User u = em.find(User.class, userId);
		tasklist.setUser(u);
		em.persist(tasklist);
	}

	public void removeTasklist(int tasklistId) {
		Tasklist tasklist = em.find(Tasklist.class, tasklistId);
		em.remove(tasklist);
	}

	public void addTask(int tasklistId, Task task) {
		int ordernum = getNextTaskOrdernum(tasklistId);
		task.setOrdernum(ordernum);
		task.setTasklist(this.getTasklist(tasklistId));
		em.persist(task);
	}

	public void removeTask(int taskId) {
		em.remove(this.getTask(taskId));
	}

	public Task getTask(int taskId) {
		Task task = em.find(Task.class, taskId);
		return task;
	}

	public Collection<Task> getTasklistTasks(int tasklistId) {
		Query q = em.createQuery("select t from Task t where t.tasklist.id=:tasklistId order by t.ordernum asc");
		q.setParameter("tasklistId", tasklistId);
		List<Task> tasks = q.getResultList();
		return tasks;
		
	}

	public void updateTasklist(Tasklist tasklist) {
		em.merge(tasklist);
	}

	public void updateTask(Task task) {
		em.merge(task);
	}

	@Override
	public Collection<Task> getTasklistTasksCompleted(int tasklistId) {
		Query q = em.createQuery("select t from Task t where t.tasklist.id=:tasklistId and t.done=1 order by t.donedate desc");
		q.setParameter("tasklistId", tasklistId);
		List<Task> tasks = q.getResultList();
		return tasks;
	}
	
	private int getNextTaskOrdernum(int tasklistId) {
		Query q = em.createQuery("select t from Task t where t.tasklist.id=:tasklistId order by t.ordernum desc");
		q.setParameter("tasklistId", tasklistId);
		try {
			Task t = (Task) q.setMaxResults(1).getSingleResult();
			return t.getOrdernum() + 1;
		}
		catch (NoResultException e) {
			return 1;
		}
		
	}

}
