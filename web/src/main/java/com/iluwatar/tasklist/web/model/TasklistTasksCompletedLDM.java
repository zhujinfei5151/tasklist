package com.iluwatar.tasklist.web.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.service.TaskService;

public class TasklistTasksCompletedLDM extends LoadableDetachableModel<List<Task>> {

	private static final long serialVersionUID = 1L;

	private int tasklistId;
	
	@SpringBean
	TaskService taskService;
	
	public TasklistTasksCompletedLDM(int tasklistId) {
		this.tasklistId = tasklistId;
		Injector.get().inject(this);
	}
	
	@Override
	protected List<Task> load() {
		return new ArrayList<>(taskService.getTasklistTasksCompleted(tasklistId));
	}

}
