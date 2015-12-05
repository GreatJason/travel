package com.firmname.travel.server.dao.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.cglib.beans.BeanMap;

import org.springframework.jdbc.core.RowMapper;

import com.firmname.travel.server.util.Logger;
import com.firmname.travel.server.util.Utils;

@SuppressWarnings("rawtypes")
public final class RowMapperClass implements RowMapper{

	private ThreadLocal<Class<?>> clazz = new ThreadLocal<Class<?>>();
	private ThreadLocal<Map<String, Object>> defaultValues = new ThreadLocal<Map<String,Object>>();
	private final static RowMapperClass instance = new RowMapperClass();
	private RowMapperClass() {
	}
	
	public static <T> RowMapperClass valueOf(Class<T> clazz){
		return valueOf(clazz, null);
	}
	
	public static <T> RowMapperClass valueOf(Class<T> clazz, Map<String, Object> defaultValues){
		instance.clazz.set(clazz);
		instance.defaultValues.set(defaultValues);
		return instance;
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Object obj = null;
		if(clazz.get() != null){
			try {
				obj = clazz.get().newInstance();
			} catch (InstantiationException e) {
				Logger.error("RowMapperClass failed to create instance!", e);
			} catch (IllegalAccessException e) {
				Logger.error("IllegalAccessException in RowMapperClass", e);
			}
		} else{
			return null;
		}
		
		BeanMap beanMap = BeanMap.create(obj);
		int colCount = rs.getMetaData().getColumnCount();
		for(int i = 1; i <= colCount; ++i){
			String columnName = rs.getMetaData().getColumnLabel(i);
			beanMap.put(Utils.columnName2FieldName(columnName), rs.getObject(i));
		}
		
		if(defaultValues.get() != null && !defaultValues.get().isEmpty()){
			for(Entry<String, Object> entry:defaultValues.get().entrySet()){
				beanMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		return obj;
	}

}
