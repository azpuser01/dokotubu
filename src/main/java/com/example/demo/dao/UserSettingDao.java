package com.example.demo.dao;

import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserToken;
import com.example.demo.entity.User_tbl;



public class UserSettingDao implements UserSettingDaoInterface{
	
	public static final String DEFAULT_SORTTYPE = "ASC";
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	
	@Autowired
	public UserSettingDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
	}

	@Override
	public Optional<UserToken> getUser(String account) {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		String sql = "select *"
					+ " from USER_TBL"
					+ " where USER_ID = :USER_ID;";
		parameters.put("ACCOUNT", account);
		
		Map<String, Object> result = namedParameterjdbcTemplate.queryForMap(sql, parameters);
		
		UserToken user = new UserToken();
		user.setAccount(account);
		user.setUserId((int)result.get("USER_ID"));
		
		return Optional.ofNullable(user);
		
	}

	@Override
	@Transactional
	public void insertUser(User_tbl user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String sql = "insert into USER_TBL " 
				+ "(ACCOUNT, USER_ID,PASS ) " 
				+"values(:ACCOUNT,:USER_ID,:PASS)";
		parameters.put("ACCOUNT",user.getAccount());
		parameters.put("PASSWORD", user.getPass());
		parameters.put("USER_ID",user.getUserId());
		
		namedParameterjdbcTemplate.update(sql, parameters);
		
	}
	

}
