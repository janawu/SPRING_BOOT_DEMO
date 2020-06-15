package com.jana.demo.pojo;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TableDO {
	
	private String table_name;
	private String table_name_uth;
	private String table_alias;
	private String privilege_role;
	private boolean is_import_complete_mode;
	private String query_sql;
	private String insert_sql;
	private String update_sql;
	private String delete_sql;
	private List<ColumnDO> columns;

	@Override
    public String toString() {
         return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


	public boolean isIs_import_complete_mode() {
		return is_import_complete_mode;
	}



	public void setIs_import_complete_mode(boolean is_import_complete_mode) {
		this.is_import_complete_mode = is_import_complete_mode;
	}



	public List<ColumnDO> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnDO> columns) {
		this.columns = columns;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_name_uth() {
		return table_name_uth;
	}

	public void setTable_name_uth(String table_name_uth) {
		this.table_name_uth = table_name_uth;
	}

	public String getTable_alias() {
		return table_alias;
	}

	public void setTable_alias(String table_alias) {
		this.table_alias = table_alias;
	}

	public String getPrivilege_role() {
		return privilege_role;
	}

	public void setPrivilege_role(String privilege_role) {
		this.privilege_role = privilege_role;
	}

	public String getQuery_sql() {
		return query_sql;
	}

	public void setQuery_sql(String query_sql) {
		this.query_sql = query_sql;
	}

	public String getInsert_sql() {
		return insert_sql;
	}

	public void setInsert_sql(String insert_sql) {
		this.insert_sql = insert_sql;
	}

	public String getUpdate_sql() {
		return update_sql;
	}

	public void setUpdate_sql(String update_sql) {
		this.update_sql = update_sql;
	}

	public String getDelete_sql() {
		return delete_sql;
	}

	public void setDelete_sql(String delete_sql) {
		this.delete_sql = delete_sql;
	}


}
