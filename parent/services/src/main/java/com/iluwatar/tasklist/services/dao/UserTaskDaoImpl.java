package com.iluwatar.tasklist.services.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.User;

@Repository("userTaskDao")
public class UserTaskDaoImpl implements UserTaskDao {
	
	@PersistenceContext
	private EntityManager em;

	public Collection<Task> findAll(int userId) {
		Query q = em.createQuery("select t from Task t where t.user.id=:userId");
		q.setParameter("userId", userId);
		return q.getResultList();  
	}

	public void addTask(int userId, Task task) {
		User user = em.find(User.class, userId);
		task.setUser(user);
		em.persist(task);
	}

	public void clearTasks(int userId) {
		Query q = em.createQuery("delete from Task t where t.user.id=:userId");
		q.setParameter("userId", userId);
		q.executeUpdate();
	}

}
