package com.iluwatar.tasklist;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

// 1. create user
// 2. login -> move to dashboard page
// 3. lost password
public class FrontPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public FrontPage(final PageParameters parameters) {
		super(parameters);
		
//		if (TasklistSession.get().getUser() != null) {
//			throw new RestartResponseException(DashboardPage.class);
//		}
		
    }
}
