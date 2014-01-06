package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.CreateTasklistPage;
import com.iluwatar.tasklist.web.page.LoginPage;

public class TestCreateTasklistPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(CreateTasklistPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}


	@Test
	public void renderPage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(CreateTasklistPage.class);

		tester.assertRenderedPage(CreateTasklistPage.class);
	}
		
}
