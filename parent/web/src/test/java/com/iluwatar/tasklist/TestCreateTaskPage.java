package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.CreateTaskPage;
import com.iluwatar.tasklist.web.page.LoginPage;

public class TestCreateTaskPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(CreateTaskPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
}
