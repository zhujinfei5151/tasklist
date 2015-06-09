package com.iluwatar.tasklist.web;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.UserService;

public class TasklistSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(TasklistSession.class);
	
	private User user;
	
	@SpringBean
	private UserService userService;
	
	public TasklistSession(Request request) {
		super(request);
        Injector.get().inject(this);
	}
	
	public static TasklistSession get() {
		return (TasklistSession) Session.get();
	}

	@Override
	public boolean authenticate(String username, String password) {
		
		logger.info("user " + username + " authenticating");

		User u = userService.getUserByUsername(username);
		if (u == null) {
			logger.info("username not found");
			return false;
		}
		String hash = TasklistUtils.md5(password + u.getSalt());
		
		if (userService.loginUser(username, hash)) {
			user = userService.getUserByUsername(username);
			logger.info("login successful");
			return true;
		}
		else {
			logger.info("login failed");
			return false;
		}

	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();
		if (isSignedIn()) {
			roles.add("USER");
		}
		return roles;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void signOut() {
		logger.info("log out " + user.getUsername());
		this.user = null;
		super.signOut();
	}	

	
}
