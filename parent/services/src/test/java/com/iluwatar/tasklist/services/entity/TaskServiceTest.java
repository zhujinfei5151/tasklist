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
public class TaskServiceTest extends BaseServiceTest {

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	
	@Before
    public void setup() {
        final String filename = "file:src/test/sql/taskservice.sql";
        runSql(filename);
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
		
		final String filename = "file:src/test/sql/inserttasklist.sql";
		runSql(filename);

		Collection<Tasklist> tasklists = taskService.getUserTasklists(1);
		assertEquals(tasklists.size(), 1);

		taskService.removeTasklist(1);
		tasklists = taskService.getUserTasklists(1);
		assertEquals(tasklists.size(), 0);
		
	}

	@Test
	public void testPersistTask() {
		
		final String filename = "file:src/test/sql/inserttasklist.sql";
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

		final String filename = "file:src/test/sql/inserttasklist.sql";
		runSql(filename);
		final String filename2 = "file:src/test/sql/inserttask.sql";
		runSql(filename2);

		Collection<Task> tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 1);
	
		taskService.removeTask(1);
		tasks = taskService.getTasklistTasks(1);
		assertEquals(tasks.size(), 0);
	}
	
}
