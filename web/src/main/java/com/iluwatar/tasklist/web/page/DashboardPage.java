package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.core.request.ClientInfo;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.web.TasklistConstants;
import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.model.UserTasklistsLDM;

@AuthorizeInstantiation("USER")
public class DashboardPage extends BasePage {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(TasklistSession.class);
	
	@SpringBean
	TaskService taskService;
	
	public DashboardPage() {
		super();
		
		// trigger once per session client info gathering here
		ClientInfo ci = getSession().getClientInfo();
		
		titleModel.setObject(getString("dashboard.header"));

		add(new FeedbackPanel("feedback"));
		
		add(new Link<Void>("addtasklist") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(CreateTasklistPage.class);
			}

		});
		
		add(new ListView<Tasklist>("items", new UserTasklistsLDM(TasklistSession.get().getUser().getId())) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Tasklist> item) {

				Link<Void> link = new Link<Void>("tasklistlink") {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						PageParameters params = new PageParameters();
						params.add(TasklistConstants.PAGE_PARAM_TASKLIST_ID, item.getModelObject().getId());
						setResponsePage(ViewTasklistPage.class, params);
					}
					
				};
				item.add(link);
				Label label = new Label("tasklistname", item.getModelObject().getName());
				link.add(label);
				
			}
			
		});
		
	}
	
}
