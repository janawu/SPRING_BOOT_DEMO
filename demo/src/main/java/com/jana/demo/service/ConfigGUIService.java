package com.jana.demo.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jana.demo.controller.DbConfig;
import com.jana.demo.pojo.UpdateDO;

@RestController
public class ConfigGUIService {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	DbConfig dbConfig;
	
	@PostMapping(value="/updateTable", consumes = "application/json", produces = "application/json")
	public String updateTable(@RequestBody UpdateDO updateDO){
		
		String tableName = updateDO.getTableName();
		List<Map<String, Object>> content = updateDO.getContent();
		
		SimpleJdbcInsert simpleJdbcInsert = 
				  new SimpleJdbcInsert(dbConfig.masterDataSource()).withTableName(tableName);
//		simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(content));
		
		// query existed data
		
		
		for(Map<String, Object> row : content) {
			
			String checkResult = checkRow(tableName, row);
			if (StringUtils.equals("success", checkResult)) {
				simpleJdbcInsert.execute(row);
			}
			row.put("checkResult", checkResult);
		}
		
		System.out.println(updateDO);
		return content.toString();
    }
	
	private String checkRow(String tableName, Map<String,?> row) {
		
		// check if existed by key in DB
		// if existed 
		// else 
		
		
		String result = "success";
		
		return result;
	}
	
	
}
