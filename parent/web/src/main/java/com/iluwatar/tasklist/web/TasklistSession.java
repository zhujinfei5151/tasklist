package com.iluwatar.tasklist.web;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.iluwatar.tasklist.services.entity.User;

public class TasklistSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;
	
	// logged in user
	private User user;

	public TasklistSession(Request request) {
		super(request);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static TasklistSession get() {
		return (TasklistSession) Session.get();
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: real implementation
		if (username.equals("abc") && password.equals("123")) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Roles getRoles() {
		// TODO: real implementation
		Roles roles = new Roles();
		if (isSignedIn()) {
			roles.add("USER");
		}
		return roles;
	}

}
