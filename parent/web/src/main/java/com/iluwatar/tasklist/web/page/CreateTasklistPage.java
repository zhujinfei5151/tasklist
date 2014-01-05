package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistSession;

@AuthorizeInstantiation("USER")
public class CreateTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	IModel<String> nameModel = Model.of("");

	@SpringBean
	TaskService taskService;
	
	public CreateTasklistPage() {
		
		Form<Void> form = new Form<>("form");
		add(form);
		
		TextField<String> nameField = new TextField<>("tasklistname", nameModel);
		form.add(nameField);
		
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
				setResponsePage(DashboardPage.class);
			}
			
		};
		form.add(submitLink);
		
	}
	
}
