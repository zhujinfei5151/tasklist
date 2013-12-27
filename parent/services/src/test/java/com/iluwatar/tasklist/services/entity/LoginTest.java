package com.iluwatar.tasklist.services.entity;

import static org.junit.Assert.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/test-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class LoginTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	UserService userService;

	@Before
    public void runSql() {
        final String filename = "file:src/test/sql/login.sql";
        Resource resource = applicationContext.getResource(filename);
        if (resource.exists()) {
            executeSqlScript(filename, false);
        } else {
            throw new RuntimeException(filename + " not found");
        }
    }
	
	@Test
	public void testLogin() {
		
		boolean result = userService.loginUser("matti", "38fe8951595f01a3c16f3d50ea0bcc53");
		assertEquals(result, true);
		
	}
	
}
