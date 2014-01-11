package com.iluwatar.tasklist.web.page;

import java.util.List;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.component.AjaxRefreshableContainer;
import com.iluwatar.tasklist.web.model.TasklistTasksNotCompletedLDM;

@AuthorizeInstantiation("USER")
public class EditTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TaskService taskService;
	
	private int tasklistId;
	
	public EditTasklistPage(PageParameters params) {
		super(params);
		
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

		AjaxRefreshableContainer container = new AjaxRefreshableContainer("container");
		add(container);

		Form<Void> form = new Form<Void>("form");
		container.add(form);
		
		final ListView<Task> listview = new ListView<Task>("tasks", new TasklistTasksNotCompletedLDM(tasklistId)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Task> item) {
				
				AjaxLink<Integer> removeLink = new AjaxLink<Integer>("remove", new PropertyModel<Integer>(item.getDefaultModel(), "id")) {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						int taskId = (int) this.getDefaultModelObject();
						target.appendJavaScript("alert('" + String.valueOf(taskId) + "');");
					}
					
				};
				item.add(removeLink);

				TextField<String> description = new TextField<>("description", new PropertyModel<String>(item.getDefaultModel(), "description"));
				item.add(description);
				
			}
			
		};
		form.add(listview);

		Link<Void> cancel = new Link<Void>("cancel") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(ViewTasklistPage.class, params);
			}
			
		};
		form.add(cancel);
		
		SubmitLink submit = new SubmitLink("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				super.onSubmit();
				IModel<List<Task>> model = (IModel<List<Task>>) listview.getDefaultModel();
				for (Task t: model.getObject()) {
					taskService.updateTask(t);
				}
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(ViewTasklistPage.class, params);
			}
			
		};
		form.add(submit);
		
	}

}
