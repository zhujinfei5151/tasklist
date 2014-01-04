package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.LoginPage;
import com.iluwatar.tasklist.web.page.ViewTasklistPage;

public class TestViewTasklistPage extends TestBase {

	@Test
	public void pageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(ViewTasklistPage.class);

		//assert rendered page class
		tester.assertRenderedPage(LoginPage.class);
	}
	
}
