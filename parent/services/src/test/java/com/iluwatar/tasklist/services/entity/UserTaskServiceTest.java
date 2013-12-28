package com.iluwatar.tasklist.services.entity;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.services.service.UserTaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/test-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserTaskServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	UserTaskService userTaskService;
	@Autowired
	UserService userService;
	
	@Before
    public void runSql() {
        final String filename = "file:src/test/sql/usertaskservice.sql";
        Resource resource = applicationContext.getResource(filename);
        if (resource.exists()) {
            executeSqlScript(filename, false);
        } else {
            throw new RuntimeException(filename + " not found");
        }
    }
	
	@Test
	public void testPersistTask() {
	
//		Collection<Task> tasks = userTaskService.findAll(1);
//		assertEquals(tasks.size(), 0);
//
//		User user = userService.getUser(1);
//		
//		Task newTask = new Task();
//		newTask.setDescription("let us do this");
//		newTask.setDone(true);
//		newTask.setUser(user);
//		
//		userTaskService.addTask(1, newTask);
//		Collection<Task> newTasks = userTaskService.findAll(1);
//		assertEquals(newTasks.size(), 1);
		
	}
	
}
