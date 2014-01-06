package com.iluwatar.tasklist;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistSession;

public class TestBase {

	protected WicketTester tester;
	
	@Before
	public void setUp()
	{
		tester = new WicketTester(new MockTasklistApplication());
	}

	public static class MockTasklistApplication extends TasklistApplication {
		
        @Override
        protected SpringComponentInjector newInjector() {
        	ApplicationContextMock ctx = new ApplicationContextMock();
            ctx.putBean(Mockito.mock(UserService.class));
            ctx.putBean(Mockito.mock(TaskService.class));
            return new SpringComponentInjector(this, ctx);
        }
        
    	@Override
    	public Session newSession(Request request, Response response) {
    		return new MockTasklistSession(request);
    	}
        
		
	}

	public static class MockTasklistSession extends TasklistSession {

		private static final long serialVersionUID = 1L;
		
		private boolean validUser = false;

		public MockTasklistSession(Request request) {
			super(request);
		}
		
		public static MockTasklistSession get() {
			return (MockTasklistSession) Session.get();
		}
		
		@Override
		public boolean authenticate(String username, String password) {
			return validUser;
		}

		public boolean isValidUser() {
			return validUser;
		}

		public void setValidUser(boolean validUser) {
			this.validUser = validUser;
			this.signIn("foo", "bar");
			this.setUser(new User());
		}
		
		
	}
}
