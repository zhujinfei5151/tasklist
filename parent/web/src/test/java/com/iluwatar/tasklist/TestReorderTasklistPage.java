package com.iluwatar.tasklist;

import org.apache.wicket.Application;
import org.junit.Test;

import com.iluwatar.tasklist.web.page.LoginPage;
import com.iluwatar.tasklist.web.page.ReorderTasklistPage;

public class TestReorderTasklistPage extends TestBase {

	@Test
	public void notLoggedInRedirectsToLoginPage()
	{
		tester.startPage(ReorderTasklistPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
	@Test
	public void pageWithoutParametersRedirectsToHomePage()
	{
		MockTasklistSession.get().setValidUser(true);
		
		tester.startPage(ReorderTasklistPage.class);

		tester.assertRenderedPage(Application.get().getHomePage());
	}
	
}
