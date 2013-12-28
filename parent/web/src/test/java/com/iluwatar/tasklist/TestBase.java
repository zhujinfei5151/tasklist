package com.iluwatar.tasklist;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.services.service.UserTaskService;
import com.iluwatar.tasklist.web.TasklistApplication;

public class TestBase {

	protected WicketTester tester;
	
	@Before
	public void setUp()
	{
        final ApplicationContextMock ctx = new ApplicationContextMock();
        ctx.putBean(Mockito.mock(UserService.class));
        ctx.putBean(Mockito.mock(UserTaskService.class));
		TasklistApplication app = new TasklistApplication() {
            @Override
            protected SpringComponentInjector newInjector() {
                return new SpringComponentInjector(this, ctx);
            }
		};
		tester = new WicketTester(app);
	}
	
}
