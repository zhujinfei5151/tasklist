package com.iluwatar.tasklist.web;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.tasklist.web.page.DashboardPage;
import com.iluwatar.tasklist.web.page.LoginPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.themes.markup.html.metro.MetroTheme;
import de.agilecoders.wicket.themes.settings.BootswatchThemeProvider;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.iluwatar.tasklist.Start#main(String[])
 */
public class TasklistApplication extends AuthenticatedWebApplication
{    	
	
	final static Logger logger = LoggerFactory.getLogger(TasklistApplication.class);
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return DashboardPage.class;
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
		
		BootstrapSettings settings = new BootstrapSettings();
		
        ThemeProvider themeProvider = new BootswatchThemeProvider() {{
            add(new MetroTheme());
            defaultTheme("readable");
        }};
        settings.setThemeProvider(themeProvider);
        List<ITheme> themes = themeProvider.available();
        for (ITheme theme: themes) {
            logger.info("available theme: " + theme.name());
        }
        settings.setThemeProvider(themeProvider);        
		
		Bootstrap.install(this, settings);
		BootstrapLess.install(this);
		
		this.getMarkupSettings().setStripWicketTags(true);
	}
	
	protected SpringComponentInjector newInjector() {
	    return new SpringComponentInjector(this);
	}

	@Override
	public Session newSession(Request request, Response response) {
		TasklistSession session = new TasklistSession(request);
		return session;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return TasklistSession.class;
	}
	
}
