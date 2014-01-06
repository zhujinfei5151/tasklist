package com.iluwatar.tasklist.services.entity;

import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

public abstract class BaseServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    protected void runSql(final String path) {
        Resource resource = applicationContext.getResource(path);
        if (resource.exists()) {
            executeSqlScript(path, false);
        } else {
            throw new RuntimeException(path + " not found");
        }
    }
	
}
