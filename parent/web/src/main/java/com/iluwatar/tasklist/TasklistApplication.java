package com.iluwatar.tasklist;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.iluwatar.tasklist.Start#main(String[])
 */
public class TasklistApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
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
	
}
