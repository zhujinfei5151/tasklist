package com.iluwatar.tasklist;

import org.junit.Test;

import com.iluwatar.tasklist.web.page.EditTasklistPage;

public class TestEditTasklistPage extends TestBase {

	@Test
	public void pageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(EditTasklistPage.class);

		//assert rendered page class
		tester.assertRenderedPage(EditTasklistPage.class);
	}
	
}
