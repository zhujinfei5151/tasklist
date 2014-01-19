package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LoginPage extends BasePage {
	private static final long serialVersionUID = 1L;

	public LoginPage(final PageParameters parameters) {
		super(parameters);

		titleModel.setObject(getString("login.header"));
		
        add(new FeedbackPanel("feedback"));
		
		final LoginForm form = new LoginForm("loginForm");
	    add(form);
	    
    }
	
	private static class LoginForm extends StatelessForm {
	 
		private static final long serialVersionUID = 1L;

		private String username;
	    private String password;
	 
	    public LoginForm(String id) {
	        super(id);
	        setModel(new CompoundPropertyModel(this));

	        Label usernameLabel = new Label("usernameLabel", getString("login.username"));
	        add(usernameLabel);
	        RequiredTextField usernameField = new RequiredTextField("username");
	        add(usernameField);
	        usernameField.setLabel(usernameLabel.getDefaultModel());
	        
	        Label passwordLabel = new Label("passwordLabel", getString("login.password"));
	        add(passwordLabel);
	        PasswordTextField passwordField = new PasswordTextField("password");
	        add(passwordField);
	        passwordField.setLabel((IModel<String>) passwordLabel.getDefaultModel());
	        
	        Link<Void> registerLink = new Link<Void>("register") {

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					setResponsePage(RegisterPage.class);
				}
	        	
	        };
	        add(registerLink);
	    }
	 
	    @Override
	    protected void onSubmit() {
	        AuthenticatedWebSession session = AuthenticatedWebSession.get();
	        if (session.signIn(username, password)) {
	        	setResponsePage(DashboardPage.class);
	        } else {
	        	error(getString("login.failed"));
	        }
	    }

	}	
}
