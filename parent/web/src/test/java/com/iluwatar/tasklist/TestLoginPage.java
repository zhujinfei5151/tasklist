package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.LoginPage;

public class TestLoginPage extends TestBase
{

	@Test
	public void pageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(LoginPage.class);

		//assert rendered page class
		tester.assertRenderedPage(LoginPage.class);
	}
	
}
