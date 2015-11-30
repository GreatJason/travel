package com.firmname.travel.server.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.cglib.beans.BeanMap;

import org.springframework.jdbc.core.RowMapper;

import com.firmname.travel.server.util.Logger;
import com.firmname.travel.server.util.Utils;

public class RowMapperClass<T> implements RowMapper<T>{

	private Class<T> clazz;
	private Map<String, Object> defaultValues;
	
	
	public RowMapperClass(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public RowMapperClass(Class<T> clazz, Map<String, Object> defaultValues) {
		this.clazz = clazz;
		this.defaultValues = defaultValues;
		
	}
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T obj = null;
		if(clazz != null){
			try {
				obj = clazz.newInstance();
			} catch (InstantiationException e) {
				Logger.error(e);
			} catch (IllegalAccessException e) {
				Logger.error(e);
			}
		} else{
			return null;
		}
		
		BeanMap beanMap = BeanMap.create(obj);
		int colCount = rs.getMetaData().getColumnCount();
		for(int i = 1; i <= colCount; ++i){
			String columnName = rs.getMetaData().getColumnName(i);
			beanMap.put(Utils.columnName2FieldName(columnName), rs.getObject(i));
		}
		
		if(defaultValues != null && !defaultValues.isEmpty()){
			for(Entry<String, Object> entry:defaultValues.entrySet()){
				beanMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		return obj;
	}

}
