package com.firmname.travel.server.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.firmname.travel.server.dao.UserDao;
import com.firmname.travel.server.dao.support.RowMapperClass;
import com.firmname.travel.server.model.User;

@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {
	
	private String insertUserSql;
	private String selectUserSql;
	
	@Override
	public void addUser(User user) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", user.getId());
		param.addValue("userName", user.getName());
		param.addValue("nickName", user.getNickName());
		param.addValue("sex", user.getSex());
		param.addValue("phone", user.getPhone());
		param.addValue("birthDate", user.getBirthDate());
		param.addValue("email", user.getEmail());
		this.getNamedParameterJdbcTemplate().update(insertUserSql, param);
	}
	
	public User getUser(String userId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<User> users = this.getNamedParameterJdbcTemplate().query(selectUserSql, param, new RowMapperClass<User>(User.class));
		return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
	}
	
	
	public void setInsertUserSql(String insertUserSql){
		this.insertUserSql = insertUserSql;
	}
	
	public void setSelectUserSql(String selectUserSql){
		this.selectUserSql = selectUserSql;
	}
}
