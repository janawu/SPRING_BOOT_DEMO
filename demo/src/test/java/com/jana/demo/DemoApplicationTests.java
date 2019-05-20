package com.jana.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({"jana.config=testValue"})
public class DemoApplicationTests {
	
	// Reference: 
	// https://stackoverflow.com/questions/39144136/using-junit-test-to-pass-command-line-argument-to-spring-boot-application
	
	@Test
	public void contextLoads() {
	}
	
//	
//	@Autowired
//    ApplicationContext ctx;
//
//    @Test
//    public void testRun() throws Exception {
//        CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
//       
//        runner.run ("--jana.config", "is arg1", "-i", "arg2");
//    }
	
	    
	    
	

}
