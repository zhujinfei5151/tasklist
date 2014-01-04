package com.iluwatar.tasklist.web.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;

public class VisibleWhenCurrentPageOfTypeBehavior extends Behavior {

	private static final long serialVersionUID = 1L;

	private Class<? extends Page> pageClass;
	
	public VisibleWhenCurrentPageOfTypeBehavior(Class<? extends Page> pageClass) {
		this.pageClass = pageClass;
	}
	
	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);
		if (component.getPage().getClass().equals(pageClass)) {
			component.setVisible(true);
		}
		else {
			component.setVisible(false);
		}
	}
	
}
