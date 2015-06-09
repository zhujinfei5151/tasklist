package com.iluwatar.tasklist.services.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		em.remove(this.getUser(userId));
	}

	public void clearUsers() {
		Query q = em.createNamedQuery("User.findAll");
		List<User> users = q.getResultList();
		for (User u: users) {
			em.remove(u);
		}
	}

	public Collection<User> findAll() {
		Query q = em.createNamedQuery("User.findAll");
		List<User> users = q.getResultList();
		return users;
	}

	public User getUser(int userId) {
		return em.find(User.class, userId);
	}

	public boolean loginUser(String username, String passwordHash) {
		Query userQuery = em.createQuery("select u from User u where u.username=:username and u.passwordHash=:passwordHash");
		userQuery.setParameter("username", username);
		userQuery.setParameter("passwordHash", passwordHash);
		try {
			User u = (User) userQuery.getSingleResult();
		}
		catch (NoResultException e) {
			return false;
		}
		return true;

	}

	public User getUserByUsername(String username) {
		Query userQuery = em.createQuery("select u from User u where u.username=:username");
		userQuery.setParameter("username", username);
		try {
			User u = (User) userQuery.getSingleResult();
			return u;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public void updateUser(User user) {
		em.merge(user);
	}

}
