package com.iluwatar.tasklist.web.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class AjaxRefreshEvent {

	private AjaxRequestTarget target;
	
	public AjaxRequestTarget getTarget() {
		return target;
	}

	public void setTarget(AjaxRequestTarget target) {
		this.target = target;
	}

	public AjaxRefreshEvent(AjaxRequestTarget target) {
		this.target = target;
	}
	
}
