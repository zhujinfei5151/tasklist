package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.DashboardPage;
import com.iluwatar.tasklist.web.page.LoginPage;

public class TestDashboardPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(DashboardPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
}
