package com.jana.demo.pojo;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ColumnDO {
	
	private String col_name;
	private String alias;
	private String data_type;
	private boolean is_key;
	private boolean is_nullable;
	private boolean is_optional;
	private List<Object> options;
	private Object default_val;
	private String valid_sql;
	
	@Override
    public String toString() {
         return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String getValid_sql() {
		return valid_sql;
	}

	public void setValid_sql(String valid_sql) {
		this.valid_sql = valid_sql;
	}

	public String getCol_name() {
		return col_name;
	}

	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public boolean isIs_key() {
		return is_key;
	}

	public void setIs_key(boolean is_key) {
		this.is_key = is_key;
	}

	public boolean isIs_optional() {
		return is_optional;
	}

	public void setIs_optional(boolean is_optional) {
		this.is_optional = is_optional;
	}

	public boolean isIs_nullable() {
		return is_nullable;
	}

	public void setIs_nullable(boolean is_nullable) {
		this.is_nullable = is_nullable;
	}

	public List<Object> getOptions() {
		return options;
	}

	public void setOptions(List<Object> options) {
		this.options = options;
	}

	public Object getDefault_val() {
		return default_val;
	}

	public void setDefault_val(Object default_val) {
		this.default_val = default_val;
	}
	
}
