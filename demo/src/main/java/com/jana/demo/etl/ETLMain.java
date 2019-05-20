package com.jana.demo.etl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jana.demo.controller.DbController;

@Component
public class ETLMain implements CommandLineRunner{
	
	@Value("${jana.config}")
	String myConfig;

	@Autowired
	DbController dbController;
	
	public void exe() {
		List<Map<String, Object>> result = dbController.queryUserByName("db1", "jana");
		System.out.println(result);
		System.out.println(myConfig);
	}

	@Override
	public void run(String... args) throws Exception {
		exe();
	}

}
