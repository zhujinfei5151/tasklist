package com.iluwatar.tasklist.services.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.service.TaskService;
import com.iluwatar.tasklist.services.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/test-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TaskServiceIT extends BaseServiceIT {

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	
	@Before
    public void setup() {
        final String filename = "classpath:createtables.sql";
        runSql(filename);
        final String filename2 = "classpath:insertuser.sql";
        runSql(filename2);
    }

	@Test
	public void testGetTasklist() {
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		
		Tasklist tasklist = taskService.getTasklist(1);
		assertNotNull(tasklist);
	}
	
	@Test
	public void testPersistTasklist() {

		User user = userService.getUser(1);
		assertNotNull(user);
		
		Collection<Tasklist> tasklists = taskService.getUserTasklists(1);
		assertEquals(tasklists.size(), 0);
		
		Tasklist tl = new Tasklist();
		tl.setName("foobar");
		taskService.addTasklist(user.getId(), tl);
		tasklists = taskService.getUserTasklists(user.getId());
		assertEquals(tasklists.size(), 1);
		
	}

	@Test
	public void testRemoveTasklist() {
		
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);

		Collection<Tasklist> tasklists = taskService.getUserTasklists(1);
		assertEquals(tasklists.size(), 1);

		taskService.removeTasklist(1);
		tasklists = taskService.getUserTasklists(1);
		assertEquals(tasklists.size(), 0);
		
	}

	@Test
	public void testUpdateTasklist() {
		
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		
		Tasklist tasklist = taskService.getTasklist(1);
		assertNotNull(tasklist);
		
		tasklist.setName("bazooka");
		taskService.updateTasklist(tasklist);
		
		tasklist = taskService.getTasklist(1);
		assertEquals(tasklist.getName(), "bazooka");
	}

	@Test
	public void testGetTask() {
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		final String filename2 = "classpath:inserttask.sql";
		runSql(filename2);
		
		Task task = taskService.getTask(1);
		assertNotNull(task);
	}
	
	@Test
	public void testPersistTask() {
		
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		
		Collection<Task> tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 0);
		
		Task task = new Task();
		task.setDescription("kuvaus");
		task.setDone(false);
		task.setDonedate(new Date());
		task.setOrdernum(1);		
		taskService.addTask(1, task);
		
		tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 1);
		
	}
	
	@Test
	public void testRemoveTask() {

		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		final String filename2 = "classpath:inserttask.sql";
		runSql(filename2);

		Collection<Task> tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 1);
	
		taskService.removeTask(1);
		tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 0);
	}
	
	@Test
	public void testUpdateTask() {
		final String filename = "classpath:inserttasklist.sql";
		runSql(filename);
		final String filename2 = "classpath:inserttask.sql";
		runSql(filename2);
		
		Task task = taskService.getTask(1);
		assertNotNull(task);
		
		task.setDone(true);
		task.setDescription("feed grandma");
		taskService.updateTask(task);
		
		task = taskService.getTask(1);
		assertEquals(task.isDone(), true);
		assertEquals(task.getDescription(), "feed grandma");
	}

}
