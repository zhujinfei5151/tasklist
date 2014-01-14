package com.iluwatar.tasklist.web.model;

import org.apache.wicket.model.AbstractReadOnlyModel;

import com.iluwatar.tasklist.web.TasklistSession;

public class ProfileButtonModel extends AbstractReadOnlyModel<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getObject() {
		String result = "";
		if (TasklistSession.get().getUser() != null) {
			result = TasklistSession.get().getUser().getName();
			if (result.equals("")) {
				result = TasklistSession.get().getUser().getUsername();
			}
		}
		return result;
	}

}
