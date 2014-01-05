package com.iluwatar.tasklist.web.page;

import java.text.SimpleDateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.event.annotation.OnEvent;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.component.AjaxRefreshableContainer;
import com.iluwatar.tasklist.web.event.AjaxRefreshEvent;
import com.iluwatar.tasklist.web.model.TaskCheckModel;
import com.iluwatar.tasklist.web.model.TasklistTasksCompletedLDM;
import com.iluwatar.tasklist.web.model.TasklistTasksNotCompletedLDM;

// create new task
// move to edit tasklist page

@AuthorizeInstantiation("USER")
public class ViewTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public ViewTasklistPage(PageParameters params) {

		
		
		// TODO: check
		StringValue sv = params.get(TasklistConstants.PAGE_PARAM_TASKLIST_ID);
		final int tasklistId = sv.toInt();

		
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
				
				CheckBox cb = new CheckBox("notcompletedcheck", new TaskCheckModel(item.getModelObject().getId()));
				item.add(cb);
				cb.add(new AjaxFormComponentUpdatingBehavior("change") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().getPage().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});

				Label description = new Label("notcompleteddescription", item.getModelObject().getDescription());
				item.add(description);
				
			}
			
		});

		
		
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
				
				CheckBox cb = new CheckBox("completedcheck", new TaskCheckModel(item.getModelObject().getId()));
				item.add(cb);
				cb.add(new AjaxFormComponentUpdatingBehavior("change") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						AjaxRefreshEvent e = new AjaxRefreshEvent(target);
						this.getComponent().getPage().send(this.getComponent().getPage(), Broadcast.BREADTH, e);
					}
					
				});

				SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String desc = "(" + dt.format(item.getModelObject().getDonedate()) + ") " + item.getModelObject().getDescription();
				Label description = new Label("completeddescription", desc);
				item.add(description);
				
			}
			
		});
		
		
		
	}

}
