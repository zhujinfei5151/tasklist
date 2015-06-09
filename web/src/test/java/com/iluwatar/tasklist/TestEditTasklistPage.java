package com.iluwatar.tasklist;

import org.apache.wicket.Application;
import org.junit.Test;

import com.iluwatar.tasklist.web.page.EditTasklistPage;
import com.iluwatar.tasklist.web.page.LoginPage;

public class TestEditTasklistPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(EditTasklistPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
	@Test
	public void pageWithoutParametersRedirectsToHomePage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(EditTasklistPage.class);

		tester.assertRenderedPage(Application.get().getHomePage());
	}
	
}
