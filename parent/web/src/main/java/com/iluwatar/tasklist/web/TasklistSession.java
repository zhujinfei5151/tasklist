package com.iluwatar.tasklist.web;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.request.Request;

import com.iluwatar.tasklist.services.entity.User;

public class TasklistSession extends Session {

	private static final long serialVersionUID = 1L;
	
	// logged in user
	private User user;

	public TasklistSession(Request request) {
		super(request);
	}

	@Override
	public ClientInfo getClientInfo() {
		return clientInfo;
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

}
