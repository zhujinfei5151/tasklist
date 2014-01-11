package com.iluwatar.tasklist.web.page;

import java.text.SimpleDateFormat;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.event.annotation.OnEvent;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.component.AjaxRefreshableContainer;
import com.iluwatar.tasklist.web.event.AjaxRefreshEvent;
import com.iluwatar.tasklist.web.model.TaskCheckModel;
import com.iluwatar.tasklist.web.model.TasklistTasksCompletedLDM;
import com.iluwatar.tasklist.web.model.TasklistTasksNotCompletedLDM;

// move to edit tasklist page

@AuthorizeInstantiation("USER")
public class ViewTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TaskService taskService;
	
	private int tasklistId;
	
	public ViewTasklistPage(PageParameters params) {
		
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
		
		//--------------------
		// not completed tasks
		//--------------------
		AjaxRefreshableContainer notCompletedContainer = new AjaxRefreshableContainer("notcompletedcontainer");
		add(notCompletedContainer);

		Form<Void> notCompletedForm = new Form<>("notcompletedform");
		notCompletedContainer.add(notCompletedForm);
		
		notCompletedForm.add(new ListView<Task>("notcompletedtasks", new TasklistTasksNotCompletedLDM(tasklistId)) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Task> item) {
				
				final TaskCheckModel checkModel = new TaskCheckModel(item.getModelObject().getId());
				CheckBox cb = new CheckBox("notcompletedcheck", checkModel);
				item.add(cb);
				cb.add(new AjaxFormComponentUpdatingBehavior("change") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});

				Label notCompletedDescription = new Label("notcompleteddescription", item.getModelObject().getDescription());
				item.add(notCompletedDescription);
				notCompletedDescription.add(new AjaxEventBehavior("click") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						checkModel.setObject(!checkModel.getObject());
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});
				
			}
			
		});
		
		//----------------
		// add task button
		//----------------
		
		Link<Void> addTaskLink = new Link<Void>("addtask") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(CreateTaskPage.class, params);
			}
			
		};
		add(addTaskLink);
		
		//----------------
		// completed tasks
		//----------------
		AjaxRefreshableContainer completedContainer = new AjaxRefreshableContainer("completedcontainer");
		add(completedContainer);

		Form<Void> completedForm = new Form<>("completedform");
		completedContainer.add(completedForm);
		
		completedForm.add(new ListView<Task>("completedtasks", new TasklistTasksCompletedLDM(tasklistId)) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Task> item) {
				
				final TaskCheckModel checkModel = new TaskCheckModel(item.getModelObject().getId());
				CheckBox cb = new CheckBox("completedcheck", checkModel);
				item.add(cb);
				cb.add(new AjaxFormComponentUpdatingBehavior("change") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});

				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String desc = "(" + dt.format(item.getModelObject().getDonedate()) + ") " + item.getModelObject().getDescription();
				Label completedDescription = new Label("completeddescription", desc);
				item.add(completedDescription);
				
				completedDescription.add(new AjaxEventBehavior("click") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						checkModel.setObject(!checkModel.getObject());
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});
				
			}
			
		});
		
	}

}
