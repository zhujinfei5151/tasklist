package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.spring.injection.annot.SpringBean;

// 1. select tasklist -> move to view tasklist page
// 2. create new tasklist
// 3. remove tasklist

@AuthorizeInstantiation("USER")
public class DashboardPage extends WebPage {

	private static final long serialVersionUID = 1L;

	
	public DashboardPage() {
		super();
			
		RepeatingView rv = new RepeatingView("items") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onPopulate() {
				super.onPopulate();
				
//				final Collection<Task> tasks = userTaskService.findAll(1);
//				
//				for (Task t: tasks) {
//					add(new Label(newChildId(), t.getDescription()));
//				}
				
			}
			
		};
		add(rv);
		
	}
	
}
