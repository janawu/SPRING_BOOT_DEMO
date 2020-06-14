package com.jana.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jana.demo.util.Constants;

@Component
public class DbControllerTest2 {
	
	Logger logger = Logger.getLogger(getClass().getName());
//	
//	@Autowired
////    @Qualifier("eesjdbc")
//    private NamedParameterJdbcTemplate jdbcTemplate;
//	
//  
//	public List<Map<String, Object>> queryUserByName(String subFab, String name) {
//
//		String sql = Constants.SQL_QUERY_USER_BY_NAME;
//		Map<String,String> paramMap = new TreeMap<>();
//		paramMap.put(Constants.PARAM_NAME, name);
//		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql,paramMap);
//
//		return result;
//	}
//	
//	public List<Map<String, Object>> queryUser(String subFab) {
//
//		String sql = Constants.SQL_QUERY_USER;
//		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql,new TreeMap<>());
//
//		return result;
//	}
//	
//	@SuppressWarnings("unchecked")
////	@Transactional
//	public void insertUserList(String subFab) {
//		
//		String testNumber = "7";
//		
//		String sql = Constants.SQL_INSERT_USER;
//		Map<String,String> paramMapA = new TreeMap<>();
//		paramMapA.put(Constants.PARAM_NAME, "aName"+testNumber);
//		paramMapA.put("address", "aAddress");
//		paramMapA.put("phone", "aPhone");
//		Map<String,String> paramMapB = new TreeMap<>();
//		paramMapB.put(Constants.PARAM_NAME, "bName"+testNumber);
//		paramMapB.put("address", "");
//		paramMapB.put("phone", "bPhone");
//		
//		List<Map<String,String>> paramList = Arrays.asList(paramMapA, paramMapB, paramMapA, paramMapB);
//		
////		dbRouter(subFab).
//		int i[] = jdbcTemplate.batchUpdate(sql, paramList.toArray(new Map[paramList.size()]));
//		for (int a : i) {
//			System.out.println("++"+a);
//		}
//		
//		
//		
//	}
}
