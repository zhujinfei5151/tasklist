package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.LoginPage;

public class TestLoginPage extends TestBase
{

	@Test
	public void pageRendersSuccessfully()
	{
		tester.startPage(LoginPage.class);

		tester.assertRenderedPage(LoginPage.class);
	}
	
}
