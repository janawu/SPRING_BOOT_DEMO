package com.jana.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.jana.demo.util.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DBControllerTest {
	
	Logger logger = Logger.getLogger(getClass().getName());

	HikariDataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

	public void dbConnect() {
    	
    	dataSource = new HikariDataSource();
    	
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://db4free.org/janadb01");
        config.setUsername("janawu");
        config.setPassword("12345678");
//        config.addDataSourceProperty("cachePrepStmts", true);
//        config.addDataSourceProperty("prepStmtCacheSize", 500);
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//        config.setConnectionTestQuery("SELECT 1");
//        config.setAutoCommit(true);
//        config.setMinimumIdle(1);
        config.setMaximumPoolSize(2);
        config.setConnectionTimeout(100000);

        dataSource = new HikariDataSource(config);
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
 
        logger.info("DB connected!");
        
    }
    
	public void shutdown(){  
	    dataSource.close();  
	}  
    
    public List<Map<String, Object>> queryUser(String subFab) {

		String sql = Constants.SQL_QUERY_USER;
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql,new TreeMap<>());

		return result;
	}

}
