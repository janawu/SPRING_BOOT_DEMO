package com.jana.demo.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
	
    
	public List<Map<String, Object>> queryUserByName(String subFab, String name) {

		String sql = Constants.SQL_QUERY_USER_BY_NAME;
		Map<String,String> paramMap = new TreeMap<>();
		paramMap.put(Constants.PARAM_NAME, name);
		List<Map<String, Object>> result = dbRouter(subFab).queryForList(sql,paramMap);

		return result;
	}
	
	public List<Map<String, Object>> queryUser(String subFab) {

		String sql = Constants.SQL_QUERY_USER;
		List<Map<String, Object>> result = dbRouter(subFab).queryForList(sql,new TreeMap<>());

		return result;
	}
	
	@SuppressWarnings("unchecked")
//	@Transactional
	public void insertUserList(String subFab) {
		
		String testNumber = "8";
		
		String sql = Constants.SQL_INSERT_USER;
		Map<String,String> paramMapA = new TreeMap<>();
		paramMapA.put(Constants.PARAM_NAME, "aName"+testNumber);
		paramMapA.put("address", "aAddress");
		paramMapA.put("phone", "aPhone");
		Map<String,String> paramMapB = new TreeMap<>();
		paramMapB.put(Constants.PARAM_NAME, "bName"+testNumber);
		paramMapB.put("address", "");
		paramMapB.put("phone", "bPhone");
		
		List<Map<String,String>> paramList = Arrays.asList(paramMapA, paramMapB, paramMapA, paramMapB);
		
		int i[] = dbRouter(subFab).batchUpdate(sql, paramList.toArray(new Map[paramList.size()]));
		for (int a : i) {
			System.out.println("++"+a);
		}
		
	}
}
