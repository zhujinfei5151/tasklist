package com.iluwatar.tasklist.services.entity;

import java.util.Collection;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.iluwatar.tasklist.services.service.UserService;
import com.iluwatar.tasklist.services.service.UserTaskService;

public class SpringTest {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();

		UserService userService = ctx.getBean("userService", UserService.class);

//		User uusi = new User();
//		uusi.setUsername("jaska");
//		uusi.setPasswordHash("sfarqwer3tewasdrf43");
//		userService.addUser(uusi);
//		User uusi2 = new User();
//		uusi2.setUsername("jaska2");
//		uusi2.setPasswordHash("sfdsfarqwer3tewasdrf43");
//		userService.addUser(uusi2);
		
//		userService.removeUser(1);

		userService.clearUsers();
		
//		UserTaskService userTaskService = ctx.getBean("userTaskService", UserTaskService.class);
//		
//		Task uusi = new Task();
//		//uusi.setId(1);
//		uusi.setDescription("jepulis");
//		uusi.setDone(false);
//		userTaskService.addTask(1, uusi);
//		
//		Collection<Task> tasks = userTaskService.findAll(1);
//		for (Task t: tasks) {
//			System.out.println(String.format("%d: %s", t.getId(), t.getDescription()));
//		}
		
	}

}
