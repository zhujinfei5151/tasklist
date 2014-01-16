package com.iluwatar.tasklist.services.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iluwatar.tasklist.services.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/test-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserServiceIT extends BaseServiceIT {

	@Autowired
	UserService userService;

	@Before
    public void setup() {
        final String filename = "classpath:createtables.sql";
        runSql(filename);
    }

	@Test
	public void testGetUser() {

        final String filename = "classpath:insertuser.sql";
        runSql(filename);

        User user = userService.getUser(1);
        assertNotNull(user);
	}
	
	@Test
	public void testPersistUser() {
		
		int count = userService.findAll().size();
		assertEquals(count,0);
		
		User u = new User();
		u.setUsername("jaska");
		u.setSalt("asdfasdf");
		u.setPasswordHash("adfasf3345345sdfasg435345");
		userService.addUser(u);

		count = userService.findAll().size();
		assertEquals(count, 1);
		
	}

	@Test
	public void testRemoveUser() {
        final String filename = "classpath:insertuser.sql";
        runSql(filename);

		int count = userService.findAll().size();
		assertEquals(count, 1);
        
		userService.removeUser(1);
		count = userService.findAll().size();
		assertEquals(count, 0);
	}
	
	@Test
	public void testUpdateUser() {
		
        final String filename = "classpath:insertuser.sql";
        runSql(filename);

		int count = userService.findAll().size();
		assertEquals(count, 1);

		User user = userService.getUser(1);
		assertNotNull(user);
		assertEquals(user.getUsername(), "matti@mainio.com");
		
		user.setUsername("pekka@pellervo.com");
		userService.updateUser(user);
		
		User updatedUser = userService.getUser(1);
		assertEquals(updatedUser.getUsername(), "pekka@pellervo.com");
		
	}
	
}
