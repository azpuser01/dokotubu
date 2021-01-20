package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.DokotubuConstant;
import com.example.demo.dto.UserToken;

/**
 * ログイン機能に関わるクエリを管理するDAOクラスの実装.
 * 
 * @since 2021-01-18
 * @author ueno
 * @author demo
 */

@Repository
public class LoginDao implements LoginDaoInterface {

	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	
	@Autowired
	public LoginDao(NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
	}

	@Override
	public Optional<DokotubuConstant> login(String account, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		String sql = "select count(account) " 
				+ "from USER_TBL "
				+ "where ACCOUNT = :ACCOUNT "
				+ "and PASS = :PASS ";

		parameters.put("ACCOUNT", account);
		parameters.put("PASS", password);
		
		
		if (namedParameterjdbcTemplate.queryForObject(sql, parameters, Integer.class) == 0) {
			return null;
		} else {
			return Optional.ofNullable(DokotubuConstant.IS_APPROVAL);
		}
	}
	
	public UserToken getUserToken(String account, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		UserToken userToken = null;

		String sql = "select count(account) " 
				+ "from USER_TBL "
				+ "where ACCOUNT = :ACCOUNT "
				+ "and PASS = :PASS ";

		parameters.put("ACCOUNT", account);
		parameters.put("PASS", password);
		
		
		if (namedParameterjdbcTemplate.queryForObject(sql, parameters, Integer.class) == 0) {
			return null;
		} else {
			return userToken;
		}
	}

}
