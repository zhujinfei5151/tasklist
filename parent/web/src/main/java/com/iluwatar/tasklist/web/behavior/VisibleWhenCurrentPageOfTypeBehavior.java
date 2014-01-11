package com.iluwatar.tasklist.web.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;

public class VisibleWhenCurrentPageOfTypeBehavior extends Behavior {

	private static final long serialVersionUID = 1L;

	private Collection<Class<? extends Page>> classes = new ArrayList<>();
	
	public VisibleWhenCurrentPageOfTypeBehavior(Class<? extends Page> ... classes) {
		this.classes.addAll(Arrays.asList(classes));
	}
	
	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);
		for (Class<? extends Page> clazz: classes) {
			if (component.getPage().getClass().equals(clazz)) {
				component.setVisible(true);
				return;
			}
		}
		component.setVisible(false);
	}
	
}
