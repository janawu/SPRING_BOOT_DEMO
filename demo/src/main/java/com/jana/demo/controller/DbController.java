package com.jana.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.jana.demo.aop.Log;
import com.jana.demo.util.Constants;

@Component
public class DbController {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
    @Qualifier("db1")
    private NamedParameterJdbcTemplate db1;
	
    @Autowired
    @Qualifier("db2")
    private NamedParameterJdbcTemplate db2;
        
    private NamedParameterJdbcTemplate dbRouter(String dbId) {
    	switch (dbId) {
    		case "db1":	return db1;
    		case "db2": return db2;
    		default:	return null;
    	}
    }
	
    
    @Log("Query user by name")
	public List<Map<String, Object>> queryUserByName(String dbId, String name) {

		String sql = Constants.SQL_QUERY_USER;
		Map<String,String> paramMap = new TreeMap<>();
		paramMap.put(Constants.PARAM_NAME, name);
		List<Map<String, Object>> result = dbRouter(dbId).queryForList(sql,paramMap);

		return result;
	}
	
}
