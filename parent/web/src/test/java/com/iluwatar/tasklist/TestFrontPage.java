package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.FrontPage;

public class TestFrontPage extends TestBase
{

	@Test
	public void pageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(FrontPage.class);

		//assert rendered page class
		tester.assertRenderedPage(FrontPage.class);
	}
	
}
