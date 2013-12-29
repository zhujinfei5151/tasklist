package com.iluwatar.tasklist.services.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

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
		Query q = em.createQuery("select t from Tasklist t where t.id=:tasklistId");
		q.setParameter("tasklistId", tasklistId);
		try {
			Tasklist t = (Tasklist) q.getSingleResult();
			return t;
		}
		catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
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

}
