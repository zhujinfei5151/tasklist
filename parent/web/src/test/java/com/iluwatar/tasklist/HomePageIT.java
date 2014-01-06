package com.iluwatar.tasklist;

import junit.framework.TestCase;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageIT extends TestCase {
	
	@Test
    public void testSomething() throws InterruptedException {
    	
    	WebDriver driver = new FirefoxDriver();
    	driver.get("http://localhost:8080");
    	assertEquals(driver.getTitle(), "Tasklist");
    	driver.quit();
        
    }	
	
}
