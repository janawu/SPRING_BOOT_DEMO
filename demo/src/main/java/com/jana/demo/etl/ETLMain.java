package com.jana.demo.etl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jana.demo.controller.DBControllerTest;
import com.jana.demo.controller.DbController;
import com.jana.demo.controller.DbControllerTest2;

@Component
public class ETLMain implements CommandLineRunner{
	
	@Value("${jana.config}")
	String myConfig;

	@Autowired
	DbController dbController;
	
	@Autowired
	DBControllerTest dbControllerTest;
	
	@Autowired
	DbControllerTest2 dbControllerTest2;
	
	public void exe() {
		List<Map<String, Object>> result = dbController.queryUserByName("db1", "jana");
		System.out.println(result);
		System.out.println(myConfig);
		dbController.insertUserList("db1");
		List<Map<String, Object>> resultAll = dbController.queryUser("db1");
		System.out.println(resultAll);
	}
	
	public void exeDBControllerTest() {
		
		dbControllerTest.dbConnect();
		List<Map<String, Object>> resultAll = dbControllerTest.queryUser("db1");
		System.out.println(resultAll);
		dbControllerTest.shutdown();
		
	}
	
	
//	public void exeTest2() {
//		List<Map<String, Object>> result = dbControllerTest2.queryUserByName("db1", "jana");
//		System.out.println(result);
////		System.out.println(myConfig);
////		dbController.insertUserList("db1");
////		List<Map<String, Object>> resultAll = dbController.queryUser("db1");
////		System.out.println(resultAll);
//	}

	@Override
	public void run(String... args) throws Exception {
//		exe();
		exeDBControllerTest();
//		exeTest2();
	}

}
