package com.jana.demo.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		
		logger.info("===== [fetchUser] invoked =====");
		List<Map<String, Object>> result = dbController.queryUserByName(dbId, name);
		System.out.println(result);
		
		logger.info("===== [fetchUser] finished =====");
        return "hello";
        
    }
	
}
