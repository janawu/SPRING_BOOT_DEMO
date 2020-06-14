package com.jana.demo.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DBConfigTest {
	
//	@Value("${fab}")
//	String fab;
//	
//	
//	@Bean(name = "eesds")
////	@ConfigurationProperties(prefix = "#{'${blog-list}'.split(',')}")
//	public DataSource secondDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//  
//	@Bean(name = "eesjdbc")
//	@Autowired
//	public NamedParameterJdbcTemplate secondaryJdbcTemplate(@Qualifier("eesds") DataSource dsMaster) {
//		return new NamedParameterJdbcTemplate(dsMaster);
//	}

}
