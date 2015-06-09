package com.iluwatar.tasklist.web.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Tasklist;
import com.iluwatar.tasklist.services.service.TaskService;

public class UserTasklistsLDM extends LoadableDetachableModel<List<Tasklist>> {

	private static final long serialVersionUID = 1L;

	private int userId;
	
	@SpringBean
	private TaskService taskService;
	
	public UserTasklistsLDM(int userId) {
		this.userId = userId;
		Injector.get().inject(this);
	}
	
	@Override
	protected List<Tasklist> load() {
		return new ArrayList<>(taskService.getUserTasklists(userId));
	}

}
