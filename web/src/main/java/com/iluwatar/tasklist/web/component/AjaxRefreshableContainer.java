package com.iluwatar.tasklist.web.component;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.wicketstuff.event.annotation.OnEvent;

import com.iluwatar.tasklist.web.event.AjaxRefreshEvent;

public class AjaxRefreshableContainer extends WebMarkupContainer {

	private static final long serialVersionUID = 1L;

	public AjaxRefreshableContainer(String id) {
		super(id);
		this.setOutputMarkupId(true);
	}
	
	@OnEvent
	public void onAjaxRefresh(AjaxRefreshEvent e) {
		e.getTarget().add(this);
	}
	
}
