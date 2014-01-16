package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.TasklistSession;

@AuthorizeInstantiation("USER")
public class CreateTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	IModel<String> nameModel = Model.of("");

	@SpringBean
	TaskService taskService;
	
	public CreateTasklistPage() {
		
		add(new FeedbackPanel("feedback"));
		
		Form<Void> form = new Form<>("form");
		add(form);
		
		TextField<String> nameField = new TextField<>("tasklistname", nameModel);
		form.add(nameField);
		nameField.setRequired(true);
		nameField.setLabel(new ResourceModel("createtasklist.tasklistname"));
		
		final CheckBox cb = new CheckBox("createanother", Model.of(false));
		form.add(cb);

		Link<Void> backLink = new Link<Void>("back") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(DashboardPage.class);
			}
			
		};
		form.add(backLink);
		
		SubmitLink submitLink = new SubmitLink("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onError() {
				super.onError();
			}

			@Override
			public void onSubmit() {
				super.onSubmit();
				int userId = TasklistSession.get().getUser().getId();
				Tasklist tl = new Tasklist();
				tl.setName(nameModel.getObject());
				taskService.addTasklist(userId, tl);
				if (!(boolean) cb.getDefaultModelObject()) {
					setResponsePage(DashboardPage.class);
				}
				else {
					nameModel.setObject("");
				}
			}
			
		};
		form.add(submitLink);
		
	}
	
}
