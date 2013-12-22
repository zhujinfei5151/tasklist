package com.iluwatar.tasklist;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
        final ApplicationContextMock ctx = new ApplicationContextMock();
		TasklistApplication app = new TasklistApplication() {
            @Override
            protected SpringComponentInjector newInjector() {
                return new SpringComponentInjector(this, ctx);
            }
		};
		tester = new WicketTester(app);
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(FrontPage.class);

		//assert rendered page class
		tester.assertRenderedPage(FrontPage.class);
	}
}
