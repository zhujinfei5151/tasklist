package com.iluwatar.tasklist.web.model;

import java.util.Date;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.iluwatar.tasklist.services.entity.Task;
import com.iluwatar.tasklist.services.service.TaskService;

public class TaskCheckModel implements IModel<Boolean> {

	private static final long serialVersionUID = 1L;

	private int taskId;
	
	@SpringBean
	private TaskService taskService;
	
	public TaskCheckModel(int taskId) {
		this.taskId = taskId;
		Injector.get().inject(this);
	}
	
	@Override
	public Boolean getObject() {
		return taskService.getTask(taskId).isDone();
	}

	@Override
	public void setObject(Boolean object) {
		Task t = taskService.getTask(taskId);
		t.setDone(object);
		if (object) {
			t.setDonedate(new Date());
		}
		else {
			t.setDonedate(null);
		}
		taskService.updateTask(t);
	}

	@Override
	public void detach() {
		// nop
	}
	
}
