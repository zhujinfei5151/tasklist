package com.iluwatar.tasklist.web.page;

import org.apache.wicket.RestartResponseException;
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
import org.apache.wicket.util.string.StringValue;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.TasklistSession;

@AuthorizeInstantiation("USER")
public class CreateTaskPage extends BasePage {

	private static final long serialVersionUID = 1L;

	IModel<String> descriptionModel = Model.of("");

	@SpringBean
	TaskService taskService;
	
	private int tasklistId;
	
	public CreateTaskPage(PageParameters params) {

		//------------------
		// handle parameters
		//------------------
		StringValue sv = params.get(TasklistConstants.PAGE_PARAM_TASKLIST_ID);
		if (sv.isNull()) {
			throw new RestartResponseException(TasklistApplication.get().getHomePage());
		}
		tasklistId = sv.toInt();
		Tasklist tl = taskService.getTasklist(tasklistId);
		if (tl == null) {
			throw new RestartResponseException(TasklistApplication.get().getHomePage());
		}
		tasklistNameModel.setObject(tl.getName());
		
		
		//------------------
		// create components
		//------------------

		titleModel.setObject(getString("createtask.header"));
		
		add(new FeedbackPanel("feedback"));
		
		Form<Void> form = new Form<>("form");
		add(form);
		
		TextField<String> descriptionField = new TextField<>("taskdescription", descriptionModel);
		form.add(descriptionField);
		descriptionField.setRequired(true);
		descriptionField.setLabel(new ResourceModel("createtask.taskdescription"));
		
		final CheckBox cb = new CheckBox("createanother", Model.of(false));
		form.add(cb);
		
		Link<Void> backLink = new Link<Void>("back") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(ViewTasklistPage.class, params);
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
				Task task = new Task();
				task.setDescription(descriptionModel.getObject());
				taskService.addTask(tasklistId, task);
				String msg = String.format(getString("createtask.created"), descriptionModel.getObject());
				if (!(boolean) cb.getDefaultModelObject()) {
					getSession().success(msg);
					PageParameters params = new PageParameters();
					params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
					setResponsePage(ViewTasklistPage.class, params);
				}
				else {
					success(msg);
					descriptionModel.setObject("");
				}
			}
			
		};
		form.add(submitLink);
		
	}
	
}
