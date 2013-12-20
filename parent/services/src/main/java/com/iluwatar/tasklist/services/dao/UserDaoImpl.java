package com.iluwatar.tasklist.services.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.iluwatar.tasklist.services.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	public void addUser(User user) {
		em.persist(user);
	}

	public void removeUser(int userId) {
		Query taskQuery = em.createQuery("delete from Task t where t.user.id=:userId");
		taskQuery.setParameter("userId", userId);
		taskQuery.executeUpdate();
		Query userQuery = em.createQuery("delete from User u where u.id=:userId");
		userQuery.setParameter("userId", userId);
		userQuery.executeUpdate();
	}

	public void clearUsers() {
		Query q = em.createQuery("select u from User u");
		List<User> users = q.getResultList();
		for (User u: users) {
			removeUser(u.getId());
		}
	}

	public Collection<User> findAll() {
		Query q = em.createQuery("select u from User u");
		List<User> users = q.getResultList();
		return users;
	}

	public User getUser(int userId) {
		Query userQuery = em.createQuery("select u from User u where u.id=:userId");
		userQuery.setParameter("userId", userId);
		User u = (User) userQuery.getSingleResult();
		return u;
	}

}
