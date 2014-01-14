package com.iluwatar.tasklist.web.page;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.TasklistUtils;

public class RegisterPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	private String password;
	private String password2;

	private RequiredTextField<String> nameField;
	private RequiredTextField<String> usernameField;
	private PasswordTextField passwordField;
	private PasswordTextField password2Field;
	private StatelessForm<RegisterPage> form;

	@SpringBean
	private UserService userService;
	
	public RegisterPage() {
		
		FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);

		form = new StatelessForm<RegisterPage>("form", new CompoundPropertyModel<>(this)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onValidate() {
				super.onValidate();
				
				if (usernameField.getConvertedInput() != null && userService.getUserByUsername(usernameField.getConvertedInput()) != null) {
					error(getString("profile.save.usernameexists"));
				}
				
				if (passwordField.getConvertedInput() != null && 
						(!passwordField.getConvertedInput().equals(password2Field.getConvertedInput()))) {
					error(getString("register.save.passwordsnotequal"));
				}
			}
			
		};
		add(form);
		
		nameField = new RequiredTextField<>("name");
		form.add(nameField);
		nameField.setLabel(new ResourceModel("register.name"));
		
		usernameField = new RequiredTextField<>("username");
		form.add(usernameField);
		usernameField.setLabel(new ResourceModel("register.username"));
		usernameField.add(EmailAddressValidator.getInstance());

		passwordField = new PasswordTextField("password");
		form.add(passwordField);
		passwordField.setLabel(new ResourceModel("register.password"));
		passwordField.add(StringValidator.minimumLength(TasklistConstants.PASSWORD_MINIMUM_LENGTH));

		password2Field = new PasswordTextField("password2");
		form.add(password2Field);
		password2Field.setLabel(new ResourceModel("register.password2"));
		password2Field.add(StringValidator.minimumLength(TasklistConstants.PASSWORD_MINIMUM_LENGTH));
		
		SubmitLink submitLink = new SubmitLink("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				User user = new User();
				user.setUsername(username);
				user.setPasswordHash(TasklistUtils.md5(password));
				user.setName(name);
				userService.addUser(user);
				TasklistSession.get().success(getString("register.save.success"));
				setResponsePage(LoginPage.class);
			}
			
		};
		form.add(submitLink);
	}
	
}
