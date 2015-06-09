package com.iluwatar.tasklist.web.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.service.TaskService;

public class TasklistTasksNotCompletedModel extends ListModel<Task> {

	private static final long serialVersionUID = 1L;

	private int tasklistId;
	
	@SpringBean
	TaskService taskService;
	
	public TasklistTasksNotCompletedModel(int tasklistId) {
		this.tasklistId = tasklistId;
		Injector.get().inject(this);
		this.setObject(load());
	}
	
	private List<Task> load() {
		return new ArrayList<>(taskService.getTasklistTasksNotCompleted(tasklistId));
	}

}
