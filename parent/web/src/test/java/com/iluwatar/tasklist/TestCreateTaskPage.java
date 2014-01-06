package com.iluwatar.tasklist;

import org.apache.wicket.Application;
import org.junit.Test;

import com.iluwatar.tasklist.TestBase.MockTasklistSession;
import com.iluwatar.tasklist.web.page.CreateTaskPage;
import com.iluwatar.tasklist.web.page.CreateTasklistPage;
import com.iluwatar.tasklist.web.page.LoginPage;

public class TestCreateTaskPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(CreateTaskPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
	@Test
	public void pageWithoutParametersRedirectsToHomePage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(CreateTaskPage.class);

		tester.assertRenderedPage(Application.get().getHomePage());
	}
	
	
}
