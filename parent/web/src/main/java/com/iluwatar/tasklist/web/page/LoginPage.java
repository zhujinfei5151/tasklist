package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

// 1. create user
// 2. login -> move to dashboard page
// 3. lost password
public class LoginPage extends BasePage {
	private static final long serialVersionUID = 1L;

	public LoginPage(final PageParameters parameters) {
		super(parameters);
		
		final LoginForm form = new LoginForm("loginForm");
	    add(form);
	    
    }
	
	private static class LoginForm extends StatelessForm {
		 
	    private static final long serialVersionUID = -6826853507535977683L;
	 
	    private String username;
	    private String password;
	 
	    public LoginForm(String id) {
	        super(id);
	        setModel(new CompoundPropertyModel(this));
	        add(new Label("usernameLabel", getString("login.username")));
	        add(new RequiredTextField("username"));
	        add(new Label("passwordLabel", getString("login.password")));
	        add(new PasswordTextField("password"));
	        add(new FeedbackPanel("feedback"));
	 
	    }
	 
	    @Override
	    protected void onSubmit() {
	        AuthenticatedWebSession session = AuthenticatedWebSession.get();
	        if (session.signIn(username, password)) {
	        	setResponsePage(DashboardPage.class);
	        } else {
	        	error("login.failed.badcredentials");
	        }
	    }

	}	
}
