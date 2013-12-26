package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.DashboardPage;

public class TestDashboardPage extends TestBase {

	@Test
	public void pageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(DashboardPage.class);

		//assert rendered page class
		tester.assertRenderedPage(DashboardPage.class);
	}
	
}
