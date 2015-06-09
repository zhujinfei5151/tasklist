package com.iluwatar.tasklist.web.page;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.googlecode.wicket.jquery.core.JQueryBehavior;
import com.googlecode.wicket.jquery.ui.effect.JQueryEffectBehavior;
import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.component.AjaxRefreshableContainer;
import com.iluwatar.tasklist.web.component.SmarterLinkLabel;
import com.iluwatar.tasklist.web.event.AjaxRefreshEvent;
import com.iluwatar.tasklist.web.model.TaskCheckModel;
import com.iluwatar.tasklist.web.model.TasklistTasksCompletedLDM;
import com.iluwatar.tasklist.web.model.TasklistTasksNotCompletedLDM;

@AuthorizeInstantiation("USER")
public class ViewTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	@SpringBean
	TaskService taskService;
	
	private int tasklistId;
	
	private AjaxRefreshableContainer notCompletedContainer;
	private AjaxRefreshableContainer completedContainer;
	
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

		titleModel.setObject(getString("viewtasklist.header"));

		add(new FeedbackPanel("feedback"));
		
		notCompletedContainer = new AjaxRefreshableContainer("notcompletedcontainer");
		add(notCompletedContainer);
		notCompletedContainer.setOutputMarkupId(true);
		add(new JQueryBehavior("#" + notCompletedContainer.getMarkupId(), "effect"));

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
						
						JQueryEffectBehavior effectBehavior = new JQueryEffectBehavior("#" + completedContainer.getMarkupId(), "highlight");
						target.appendJavaScript(effectBehavior.toString());
					}
					
				});

				Label notCompletedDescription = new SmarterLinkLabel("notcompleteddescription", item.getModelObject().getDescription());
				item.add(notCompletedDescription);
				
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
		// edit button
		//----------------
		
		Link<Void> editLink = new Link<Void>("edit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(EditTasklistPage.class, params);
			}
			
		};
		add(editLink);

		//----------------
		// reorder button
		//----------------
		
		Link<Void> reorder = new Link<Void>("reorder") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(ReorderTasklistPage.class, params);
			}
			
		};
		add(reorder);
		
		//----------------
		// completed tasks
		//----------------
		completedContainer = new AjaxRefreshableContainer("completedcontainer");
		add(completedContainer);
		completedContainer.setOutputMarkupId(true);

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
						
						JQueryEffectBehavior effectBehavior = new JQueryEffectBehavior("#" + notCompletedContainer.getMarkupId(), "highlight");
						target.appendJavaScript(effectBehavior.toString());
					}
					
				});

				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				
				ClientInfo clientInfo = getSession().getClientInfo();
				if (clientInfo instanceof WebClientInfo)
				{
					WebClientInfo webClientInfo = (WebClientInfo)clientInfo;
					TimeZone tz = webClientInfo.getProperties().getTimeZone();
					if (tz != null) {
						dt.setTimeZone(tz);
					}
				}
				
				String desc = "(" + dt.format(item.getModelObject().getDonedate()) + ") " + item.getModelObject().getDescription();
				Label completedDescription = new SmarterLinkLabel("completeddescription", desc);
				item.add(completedDescription);
				
			}
			
		});
		
	}

}
