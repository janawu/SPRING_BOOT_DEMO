package com.jana.demo.service;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jana.demo.aop.Log;
import com.jana.demo.controller.DbController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class Service {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	DbController dbController;
	
	@GetMapping(value = "/user")
	public String fetchUser(@RequestParam String dbId, @RequestParam String name){
			
		List<Map<String, Object>> result = dbController.queryUserByName(dbId, name);
		System.out.println(result);
		
        return "hello";
        
    }
	
	@GetMapping(value = "/process-test")
	public String fetchProcess() throws IOException{
		String pythonExe = "/Library/Frameworks/Python.framework/Versions/3.5/bin/python3";
//        ProcessBuilder pb = new ProcessBuilder().command(pythonExe, "pyTest.py", "1", "2", "3");   
        ProcessBuilder pb =  new ProcessBuilder().command(pythonExe, "pyTest.py", "1", "2", "3")
        		.redirectOutput(
        				Redirect.to(new File("hi.txt")));
        
        pb.directory(new File(System.getProperty("user.dir")));  
        Map<String, String> map = pb.environment();  
//        System.out.println(map);
        Process p1 = pb.start();
        
		return "hi";
	}
	
	
	
}
