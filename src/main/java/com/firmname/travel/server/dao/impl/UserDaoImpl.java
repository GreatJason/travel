package com.firmname.travel.server.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.firmname.travel.server.dao.UserDao;
import com.firmname.travel.server.dao.support.RowMapperClass;
import com.firmname.travel.server.model.ErrorCode;
import com.firmname.travel.server.model.User;

@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {
	
	private String insertUserSql;
	private String selectUserByIdSql;
	private String selectUserByPhonePasswordSql;
	private String selectUserByEmailPasswordSql;
	
	@Override
	public ErrorCode addUser(User user) {
		//TODO: list all properties in user
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", user.getId());
		param.addValue("userName", user.getName());
		param.addValue("nickName", user.getNickName());
		param.addValue("sex", user.getSex());
		param.addValue("phone", user.getPhone());
		param.addValue("birthDate", user.getBirthDate());
		param.addValue("email", user.getEmail());
		param.addValue("password", user.getPassword());
		//TODO: check user existing or not
		return 1 == getNamedParameterJdbcTemplate().update(insertUserSql, param) ? ErrorCode.Success : ErrorCode.DB_ERROR;
	}
	@Override
	public User getUserById(String userId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<User> users = this.getNamedParameterJdbcTemplate().query(selectUserByIdSql, param, new RowMapperClass<User>(User.class));
		return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
	}
	
	@Override
	public User getUserByPhone(String phone, String password) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("phone", phone);
		param.addValue("password", password);
		List<User> users = this.getNamedParameterJdbcTemplate().query(selectUserByPhonePasswordSql, param, new RowMapperClass<User>(User.class));
		return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
	}
	
	@Override
	public User getUserByEmail(String email, String password) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("email", email);
		param.addValue("password", password);
		List<User> users = this.getNamedParameterJdbcTemplate().query(selectUserByEmailPasswordSql, param, new RowMapperClass<User>(User.class));
		return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
	}
	
	public void setInsertUserSql(String insertUserSql){
		this.insertUserSql = insertUserSql;
	}
	
	public void setSelectUserByIdSql(String selectUserByIdSql){
		this.selectUserByIdSql = selectUserByIdSql;
	}

	public void setSelectUserByPhonePasswordSql(String selectUserByPhonePasswordSql){
		this.selectUserByPhonePasswordSql = selectUserByPhonePasswordSql;
	}
	
	public void setSelectUserByEmailPasswordSql(String selectUserByEmailPasswordSql){
		this.selectUserByEmailPasswordSql = selectUserByEmailPasswordSql;
	}
}
