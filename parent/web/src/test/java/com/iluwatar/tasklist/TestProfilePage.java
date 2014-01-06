package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.LoginPage;
import com.iluwatar.tasklist.web.page.ProfilePage;

public class TestProfilePage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(ProfilePage.class);

		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void renderPage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(ProfilePage.class);

		tester.assertRenderedPage(ProfilePage.class);
	}
	
}
