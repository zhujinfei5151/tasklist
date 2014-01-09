package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.RegisterPage;

public class TestRegisterPage extends TestBase
{

	@Test
	public void pageRendersSuccessfully()
	{
		tester.startPage(RegisterPage.class);

		tester.assertRenderedPage(RegisterPage.class);
	}
	
}
