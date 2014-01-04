package com.iluwatar.tasklist.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.iluwatar.tasklist.model.TaskCheckModel;
import com.iluwatar.tasklist.model.TasklistTasksLDM;
import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.web.TasklistConstants;

// 1. list tasks that are not completed
// 2. mark task completed
// 3. display completed tasks
// 4. move to edit tasklist page

@AuthorizeInstantiation("USER")
public class ViewTasklistPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	public ViewTasklistPage(PageParameters params) {
		
		StringValue sv = params.get(TasklistConstants.PAGE_PARAM_TASKLIST_ID);
		final int tasklistId = sv.toInt();

		Form<Void> form = new Form<>("form");
		add(form);
		
		form.add(new ListView<Task>("task", new TasklistTasksLDM(tasklistId)) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void populateItem(ListItem<Task> item) {
				
				CheckBox cb = new CheckBox("check", new TaskCheckModel(item.getModelObject().getId()));
				item.add(cb);
				cb.add(new AjaxFormComponentUpdatingBehavior("change") {

					private static final long serialVersionUID = 1L;

					@Override
					protected void onUpdate(AjaxRequestTarget target) {
						// nop
					}
					
				});

				Label description = new Label("description", item.getModelObject().getDescription());
				item.add(description);
				
			}
			
		});
		
	}

}
