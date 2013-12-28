package com.iluwatar.tasklist.web;

import java.security.MessageDigest;

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

		String hash = MD5(password);
		logger.info("hash={}", hash);
		
		if (userService.loginUser(username, hash)) {
			user = userService.getUserByUsername(username);
			return true;
		}
		else {
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
	
	private String MD5(String md5) {
		// TODO: rewrite
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} 
		catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

}
