package com.iluwatar.tasklist.services.initializer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TasklistInitializerBean implements InitializingBean, ApplicationContextAware {
	
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		
//        String targetMode = System.getenv().get("TASKLIST_MODE");
//        boolean developmentMode = ((targetMode != null) && (targetMode.equals("DEVELOPMENT")));
//        if (developmentMode) {
//        }
        
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
