package com.jana.demo.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Configuration
public class DbConfig {
	
	@Bean(name = "ds1")
	@Primary
	@ConfigurationProperties(prefix="db1")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "ds2")
	@ConfigurationProperties(prefix="db2")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}
   

	@Bean(name = "db1")
	@Autowired
	public NamedParameterJdbcTemplate masterJdbcTemplate(@Qualifier("ds1") DataSource dsMaster) {
		return new NamedParameterJdbcTemplate(dsMaster);
	}

	@Bean(name = "db2")
	@Autowired
	public NamedParameterJdbcTemplate secondaryJdbcTemplate(@Qualifier("ds2") DataSource dsMaster) {
		return new NamedParameterJdbcTemplate(dsMaster);
	}

}
