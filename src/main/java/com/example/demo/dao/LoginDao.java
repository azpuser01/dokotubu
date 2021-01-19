package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.DokotubuConstant;

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

	@Override
	public Optional<DokotubuConstant> login(String account, String password) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		String sql = "select count(ACCOUNT) " 
				+ "from USER_TBL"
				+ "where ACCOUNT = :ACCOUNT "
				+ "and PASSWORD = :PASSWORD";

		parameters.put("ACCOUNT", account);
		parameters.put("PASSWORD", password);

		if (namedParameterjdbcTemplate.queryForObject(sql, parameters, Integer.class) == 0) {
			return null;
		} else {
			return Optional.ofNullable(DokotubuConstant.IS_APPROVAL);
		}
	}

}
