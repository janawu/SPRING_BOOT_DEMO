package com.jana.demo.service;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jana.demo.controller.DbController;
import com.jana.demo.pojo.ColumnDO;
import com.jana.demo.pojo.TableDO;
import com.jana.demo.pojo.UpdateDO;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class GUIService {
	
	private Logger logger = LoggerFactory.getLogger(GUIService.class);
	
	@Autowired
	private DbController dbController;
	
	/**
	 *	Query table current data
	 */
	@GetMapping(value="/query", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> queryTable(@RequestParam("table") String tableName){
		
		List<Map<String, Object>> data = new ArrayList<>();
		
    	Optional<TableDO> _t = getTableMetaData(tableName);
		if (_t.isPresent()) {
			String querySql = _t.get().getQuery_sql();
			data = dbController.queryTable(querySql);
		}
		
		return data;
	}
	
	/**
	 *	Get configuration table list with metadata
	 */
	@GetMapping(value="/configTableList", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TableDO> queryConfigTableList(){
		
		List<TableDO> tableList = getTableMetaData();
		return tableList;
		
	}
	
	@PostMapping(value="/updateTable", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> updateTable(@RequestBody UpdateDO updateDO) {
		
		String tableName = updateDO.getTableName();
		String updateUser = updateDO.getUpdateUser();
		List<Map<String, Object>> content = updateDO.getContent();
		
		/* query existed data */
		Optional<TableDO> _t = getTableMetaData(tableName);
		if (!_t.isPresent()) {
			logger.error("Table [{}] not defined", tableName);
			return genErrMsgToReturn("Error - Table ["+tableName+"] not defined");
		}
		String querySql = _t.get().getQuery_sql();
		String insertSql = _t.get().getInsert_sql();
		String updateSql = _t.get().getUpdate_sql();
		List<ColumnDO> colsDef = _t.get().getColumns();
		List<String> keyColsName = colsDef.stream()
								.filter(j->j.isIs_key() == true)
								.map(j->j.getCol_name()).collect(Collectors.toList());
		List<Map<String, Object>> curData = dbController.queryTable(querySql);
		
		/* distinguish data to update or data to insert */
		List<Map<String, Object>> toUpdateList = new ArrayList<>();
		List<Map<String, Object>> toInsertList = new ArrayList<>();
		
		if (keyColsName.isEmpty()) {
			toInsertList = curData;
		}
		else {
			// check if duplicate key columns existed 
			Set<Map<String, Object>> _s = content.stream().map(j->getKeyColMap(j, keyColsName)).collect(Collectors.toSet());
			if (_s.size() != content.size()) {
				logger.error("Duplicate key columns are found");
				return genErrMsgToReturn("Error - Duplicate key columns are found");
			}
			
			// loop each row of new data
			for(Map<String, Object> newRow : content) {
				
				boolean keyRowExisted = false;
				
				// get key columns map of row
				Map<String, Object> newRowKeyColMap = getKeyColMap(newRow, keyColsName);
				
				// loop each row of current data
				for (Map<String, Object> curRow : curData) {
					
					// no change
					if (newRow.equals(curRow)) {
						logger.info("no change");
						keyRowExisted = true;
						break;
					}
					
					// change non-key value -> to update
					Map<String, Object> curRowKeyColMap = getKeyColMap(curRow, keyColsName);
					if (newRowKeyColMap.equals(curRowKeyColMap)) {
						logger.info("to update");
						toUpdateList.add(newRow);
						keyRowExisted = true;
						break;
					}
					
				}
				
				// key not duplicate with current data -> to insert
				if (keyRowExisted == false) {
					logger.info("to insert");
					toInsertList.add(newRow);
				}
			}
		}
		
		/* Handle toInsertList */
		toInsertList.forEach(j->j.put("update_user", updateUser));
		List<Map<String, Object>> insertResult = dbController.insertTable(insertSql, toInsertList);
		
		/* Handle toUpdateList */
		toUpdateList.forEach(j->j.put("update_user", updateUser));
		List<Map<String, Object>> updateResult = dbController.updateTable(updateSql, toUpdateList);
		
		List<Map<String, Object>> result = insertResult;
		result.addAll(updateResult);
		
		return result;
    }
	

	
	private Optional<TableDO> getTableMetaData(String tableName) {
		
		try {
			URL url = this.getClass().getResource("/TABLE.json");
			List<TableDO> tables = new ObjectMapper().readValue(new File(new URI(url.toString())), new TypeReference<List<TableDO>>(){});
			Optional<TableDO> _t = tables.stream().filter(j->StringUtils.equals(tableName, j.getTable_name())).findFirst();
			return _t;
			
		}
		catch(Exception e) {
			logger.error("Get table [{}] metadata error : ", tableName, e);
			return Optional.empty();
		}
		
	}
	
	private List<TableDO> getTableMetaData(){
		
		try {
			URL url = this.getClass().getResource("/TABLE.json");
			List<TableDO> tables = new ObjectMapper().readValue(new File(new URI(url.toString())), new TypeReference<List<TableDO>>(){});
			return tables;
		}
		catch(Exception e) {
			logger.error("Get table metadata error :", e);
			return new ArrayList<>();
		}
		
	}
	
	private Map<String, Object> getKeyColMap(Map<String, Object> row, List<String> keyColsName) {
		return row.entrySet().stream()
				.filter(j->keyColsName.contains(j.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	private List<Map<String, Object>> genErrMsgToReturn(String errMsg){
		Map<String, Object> m = new HashMap<>();
		m.put("ErrMsg", (Object) errMsg);
		return Arrays.asList(m);
	}
	
	//TODO check nullable
	//TODO is import complete mode
	//TODO table_name_uth

}
