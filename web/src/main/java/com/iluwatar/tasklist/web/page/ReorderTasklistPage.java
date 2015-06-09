package com.iluwatar.tasklist.web.page;

import java.util.List;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import com.googlecode.wicket.jquery.ui.interaction.sortable.Sortable;
import com.googlecode.wicket.jquery.ui.interaction.sortable.Sortable.HashListView;
import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistApplication;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.model.TasklistTasksNotCompletedModel;

@AuthorizeInstantiation("USER")
public class ReorderTasklistPage extends BasePage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	TaskService taskService;
	
	private int tasklistId;
	
	public ReorderTasklistPage(PageParameters params) {
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
		
		//-----------
		// components
		//-----------

		titleModel.setObject(getString("reordertasklist.header"));
		
		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
		feedback.setOutputMarkupId(true);
		
		final Sortable<Task> sortable = new Sortable<Task>("sortable", new TasklistTasksNotCompletedModel(tasklistId)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected HashListView<Task> newListView(IModel<List<Task>> model)
			{
				return ReorderTasklistPage.newListView("items", model);
			}

			@Override
			public void onUpdate(AjaxRequestTarget target, Task item, int index)
			{
				// Will update the model object with the new order
				// Remove the call to super if you do not want your model to be updated (or you use a LDM)
				super.onUpdate(target, item, index);

//				this.info(String.format("'%s' has moved to position %d", item.getDescription(), index + 1));
//				this.info("The list order is now: " + this.getModelObject());
//				target.add(feedback);
				
				int order = 1;
				for (Task t: this.getModelObject()) {
					t.setOrdernum(order);
					taskService.updateTask(t);
					order++;
				}
			}
		};

		this.add(sortable);		
		
		Link<Void> doneLink = new Link<Void>("done") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters params = new PageParameters();
				params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, tasklistId);
				setResponsePage(ViewTasklistPage.class, params);
			}
			
		};
		add(doneLink);
		
	}
	
	protected static HashListView<Task> newListView(String id, IModel<List<Task>> model)
	{
		return new HashListView<Task>(id, model) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Task> item)
			{
				item.add(new Label("item", item.getModelObject().getDescription()));
			}
		};
	}	

}
