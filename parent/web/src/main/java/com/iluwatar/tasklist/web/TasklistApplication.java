package com.iluwatar.tasklist.web;

import java.util.Collection;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.web.page.FrontPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.iluwatar.tasklist.Start#main(String[])
 */
public class TasklistApplication extends AuthenticatedWebApplication
{    	
	
	final static Logger logger = LoggerFactory.getLogger(TasklistApplication.class);
	
	@SpringBean
	UserService userService;
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return FrontPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

        SpringComponentInjector spring = newInjector();
		getComponentInstantiationListeners().add(spring);
		getBehaviorInstantiationListeners().add(spring);
		spring.inject(this);
	}
	
	protected SpringComponentInjector newInjector() {
	    return new SpringComponentInjector(this);
	}

	@Override
	public Session newSession(Request request, Response response) {
		TasklistSession session = new TasklistSession(request);
		
		Collection<User> users = userService.findAll();
		logger.info("user count={}", users.size());
		if (users.size() > 0) {
			User user = users.iterator().next();
			session.setUser(user);
			logger.info("user={}", user.getUsername());
		}

		return session;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return FrontPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return TasklistSession.class;
	}
	
}
