package com.jana.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class DbController {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
    @Qualifier("db1")
    private NamedParameterJdbcTemplate db1;
	
//    @Autowired
//    @Qualifier("db2")
//    private NamedParameterJdbcTemplate db2;
        
    private NamedParameterJdbcTemplate dbRouter(String dbId) {
    	switch (dbId) {
    		case "db1":	return db1;
//    		case "db2": return db2;
    		default:	return null;
    	}
    }
	
   
	
	public List<Map<String, Object>> queryTable(String sql) {

		List<Map<String, Object>> result = dbRouter("db1").queryForList(sql,new TreeMap<>());

		return result;
	}
	
	public List<Map<String, Object>> insertTable(String sql, List<Map<String,Object>> data) {
		
		data.forEach(d->{
			try {
				dbRouter("db1").update(sql, d);
				d.put("admin_msg", "insert success");
			}
			catch(Exception e) {
				d.put("admin_msg", "insert failed - " + e);
			}
		});
		
		return data;
		
	}
	
	public List<Map<String, Object>> updateTable(String sql, List<Map<String,Object>> data) {
		
		data.forEach(d->{
			try {
				dbRouter("db1").update(sql, d);
				d.put("admin_msg", "update success");
			}
			catch(Exception e) {
				d.put("admin_msg", "update failed - " + e);
			}
		});
		
		return data;
		
	}
	
}
