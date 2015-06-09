package com.iluwatar.tasklist.web.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

import com.iluwatar.tasklist.web.TasklistSession;

public class VisibleWhenLoggedInBehavior extends Behavior {

	private static final long serialVersionUID = 1L;

	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);
		TasklistSession session = TasklistSession.get();
		component.setVisible(session.isSignedIn());
	}
	
}
