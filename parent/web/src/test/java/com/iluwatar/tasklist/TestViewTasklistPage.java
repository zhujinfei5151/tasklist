package com.iluwatar.tasklist;

import org.apache.wicket.Application;
import org.junit.Test;

import com.iluwatar.tasklist.web.page.DashboardPage;
import com.iluwatar.tasklist.web.page.LoginPage;
import com.iluwatar.tasklist.web.page.ViewTasklistPage;

public class TestViewTasklistPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(ViewTasklistPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
	@Test
	public void pageWithoutParametersRedirectsToHomePage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(ViewTasklistPage.class);

		tester.assertRenderedPage(Application.get().getHomePage());
	}
	
}
