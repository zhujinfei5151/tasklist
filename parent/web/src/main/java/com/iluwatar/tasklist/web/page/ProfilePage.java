package com.iluwatar.tasklist.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.event.annotation.OnEvent;

import com.iluwatar.tasklist.services.entity.User;
import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.TasklistUtils;
import com.iluwatar.tasklist.web.component.AjaxRefreshableContainer;
import com.iluwatar.tasklist.web.event.AjaxRefreshEvent;

@AuthorizeInstantiation("USER")
public class ProfilePage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	private String password;
	private String password2;
	
	private RequiredTextField<String> nameField;
	private RequiredTextField<String> usernameField;
	private PasswordTextField passwordField;
	private PasswordTextField password2Field;
	private AjaxLink<Void> cancelNameLink;
	private AjaxLink<Void> editNameLink;
	private SubmitLink saveNameLink;
	private AjaxLink<Void> cancelUsernameLink;
	private AjaxLink<Void> editUsernameLink;
	private SubmitLink saveUsernameLink;
	private AjaxLink<Void> cancelPasswordLink;
	private AjaxLink<Void> editPasswordLink;
	private SubmitLink savePasswordLink;
	
	private AjaxRefreshableContainer formContainer;
	private Form nameForm;
	private Form usernameForm;
	private Form passwordForm;
	
	@SpringBean
	private UserService userService;
	
	public ProfilePage() {

		titleModel.setObject(getString("profile.header"));
		
		//---------
		// feedback
		//---------
		add(new FeedbackPanel("feedback"));
		
		//----------
		// container
		//----------
		formContainer = new AjaxRefreshableContainer("formContainer");
		add(formContainer);

		//----------------
		// name components
		//----------------
		nameForm = new Form("nameForm", new CompoundPropertyModel(this));
		formContainer.add(nameForm);

		nameField = new RequiredTextField<>("name");
		nameForm.add(nameField);
		nameField.setLabel(new ResourceModel("profile.name"));

		cancelNameLink = new AjaxLink<Void>("cancelName") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				enableNameFields(false);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		nameForm.add(cancelNameLink);
		
		editNameLink = new AjaxLink<Void>("editName") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				name = TasklistSession.get().getUser().getName();
				enableNameFields(true);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		nameForm.add(editNameLink);
		
		saveNameLink = new SubmitLink("saveName") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				
				User user = userService.getUser(TasklistSession.get().getUser().getId());
				user.setName(name);
				userService.updateUser(user);
				TasklistSession.get().setUser(user);
				
				enableNameFields(false);
				success(getString("profile.save.name.success"));
			}
			
		};
		nameForm.add(saveNameLink);
		
		//--------------------
		// username components
		//--------------------
		usernameForm = new Form("usernameForm", new CompoundPropertyModel(this)) {

			@Override
			protected void onValidate() {
				super.onValidate();
				boolean sameUsername = TasklistSession.get().getUser().getUsername().equals(usernameField.getConvertedInput());
				if (!sameUsername && userService.getUserByUsername(usernameField.getConvertedInput()) != null) {
					error(getString("profile.save.usernameexists"));
				}
			}
			
		};
		formContainer.add(usernameForm);

		usernameField = new RequiredTextField<>("username");
		usernameForm.add(usernameField);
		usernameField.setLabel(new ResourceModel("profile.username"));
		usernameField.add(EmailAddressValidator.getInstance());

		cancelUsernameLink = new AjaxLink<Void>("cancelUsername") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				enableUsernameFields(false);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		usernameForm.add(cancelUsernameLink);
		
		editUsernameLink = new AjaxLink<Void>("editUsername") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				username = TasklistSession.get().getUser().getUsername();
				enableUsernameFields(true);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		usernameForm.add(editUsernameLink);
		
		saveUsernameLink = new SubmitLink("saveUsername") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				
				User user = userService.getUser(TasklistSession.get().getUser().getId());
				user.setUsername(username);
				userService.updateUser(user);
				TasklistSession.get().setUser(user);
				
				enableUsernameFields(false);
				success(getString("profile.save.username.success"));
			}
			
		};
		usernameForm.add(saveUsernameLink);
		
		//--------------------
		// password components
		//--------------------
		passwordForm = new Form("passwordForm", new CompoundPropertyModel(this)) {

			@Override
			protected void onValidate() {
				super.onValidate();
				if (passwordField.getConvertedInput() != null && 
						(!passwordField.getConvertedInput().equals(password2Field.getConvertedInput()))) {
					error(getString("profile.save.passwordsnotequal"));
				}
			}
			
		};
		formContainer.add(passwordForm);
				
		passwordField = new PasswordTextField("password");
		passwordForm.add(passwordField);
		passwordField.setLabel(new ResourceModel("profile.password"));
		passwordField.add(StringValidator.minimumLength(TasklistConstants.PASSWORD_MINIMUM_LENGTH));

		password2Field = new PasswordTextField("password2");
		passwordForm.add(password2Field);
		password2Field.setLabel(new ResourceModel("profile.password2"));
		password2Field.add(StringValidator.minimumLength(TasklistConstants.PASSWORD_MINIMUM_LENGTH));
		
		cancelPasswordLink = new AjaxLink<Void>("cancelPassword") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				enablePasswordFields(false);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		passwordForm.add(cancelPasswordLink);

		editPasswordLink = new AjaxLink<Void>("editPassword") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				password = "";
				password2 = "";
				enablePasswordFields(true);
				this.send(this, Broadcast.BUBBLE, new AjaxRefreshEvent(target));
			}
			
		};
		passwordForm.add(editPasswordLink);
		
		savePasswordLink = new SubmitLink("savePassword") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				
				User user = userService.getUser(TasklistSession.get().getUser().getId());
				user.setUsername(username);
				user.setPasswordHash(TasklistUtils.md5(password));
				userService.updateUser(user);
				TasklistSession.get().setUser(user);
				
				enablePasswordFields(false);
				success(getString("profile.save.password.success"));
			}
			
		};
		passwordForm.add(savePasswordLink);
		
		//------------
		// init values
		//------------
		name = TasklistSession.get().getUser().getName();
		username = TasklistSession.get().getUser().getUsername();
		
		//--------------------------
		// disable editing initially
		//--------------------------
		enableNameFields(false);
		enableUsernameFields(false);
		enablePasswordFields(false);
	}

	private void enableNameFields(boolean enable) {
		nameField.setEnabled(enable);
		cancelNameLink.setEnabled(enable);
		editNameLink.setEnabled(!enable);
		saveNameLink.setEnabled(enable);
		
		editUsernameLink.setEnabled(!enable);
		editPasswordLink.setEnabled(!enable);
	}
	
	private void enableUsernameFields(boolean enable) {
		usernameField.setEnabled(enable);
		cancelUsernameLink.setEnabled(enable);
		editUsernameLink.setEnabled(!enable);
		saveUsernameLink.setEnabled(enable);
		
		editNameLink.setEnabled(!enable);
		editPasswordLink.setEnabled(!enable);
	}
	
	private void enablePasswordFields(boolean enable) {
		passwordField.setEnabled(enable);
		password2Field.setEnabled(enable);
		cancelPasswordLink.setEnabled(enable);
		editPasswordLink.setEnabled(!enable);
		savePasswordLink.setEnabled(enable);
		
		editNameLink.setEnabled(!enable);
		editUsernameLink.setEnabled(!enable);
	}
	
}
