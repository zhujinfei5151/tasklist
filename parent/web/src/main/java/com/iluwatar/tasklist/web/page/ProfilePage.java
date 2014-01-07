package com.iluwatar.tasklist.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.TasklistUtils;

@AuthorizeInstantiation("USER")
public class ProfilePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String password2;
	
	private RequiredTextField<String> usernameField;
	private PasswordTextField passwordField;
	private PasswordTextField password2Field;
	private AjaxLink<Void> editLink;
	private SubmitLink saveLink;
	
	@SpringBean
	private UserService userService;
	
	public ProfilePage() {
		
		add(new FeedbackPanel("feedback"));
		
		Form form = new Form("form", new CompoundPropertyModel(this)) {

			@Override
			protected void onValidate() {
				super.onValidate();
				boolean sameUsername = TasklistSession.get().getUser().getUsername().equals(usernameField.getConvertedInput());
				if (!sameUsername && userService.getUserByUsername(username) != null) {
					error(getString("profile.save.usernameexists"));
				}
				if (passwordField.getConvertedInput() != null && 
						(!passwordField.getConvertedInput().equals(password2Field.getConvertedInput()))) {
					error(getString("profile.save.passwordsnotequal"));
				}
			}
			
		};
		add(form);
		
		usernameField = new RequiredTextField<>("username");
		form.add(usernameField);
		usernameField.setLabel(new ResourceModel("profile.username"));
		
		passwordField = new PasswordTextField("password");
		form.add(passwordField);
		passwordField.setLabel(new ResourceModel("profile.password"));

		password2Field = new PasswordTextField("password2");
		form.add(password2Field);
		password2Field.setLabel(new ResourceModel("profile.password2"));
		
		editLink = new AjaxLink<Void>("edit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				enableFields(true);
				target.add(this.getParent());
			}
			
		};
		form.add(editLink);
		
		saveLink = new SubmitLink("save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onError() {
				super.onError();
			}

			@Override
			public void onSubmit() {
				super.onSubmit();
				
				User user = userService.getUser(TasklistSession.get().getUser().getId());
				user.setUsername(username);
				user.setPasswordHash(TasklistUtils.md5(password));
				userService.updateUser(user);
				TasklistSession.get().setUser(user);
				
				enableFields(false);
				success(getString("profile.save.success"));
			}
			
		};
		form.add(saveLink);
		
		enableFields(false);
	}

	private void enableFields(boolean enable) {
		usernameField.setEnabled(enable);
		passwordField.setEnabled(enable);
		password2Field.setEnabled(enable);
		editLink.setEnabled(!enable);
		saveLink.setEnabled(enable);
	}
}
