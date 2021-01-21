package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserToken;
import com.example.demo.form.RegistationForm;

@Repository
public class UserSettingDao implements UserSettingDaoInterface {

	public static final String DEFAULT_SORTTYPE = "ASC";

	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;

	@Autowired
	public UserSettingDao(NamedParameterJdbcTemplate namedParameterjdbcTemplate) {

		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
	}

	@Override
	public Optional<UserToken> getUser(String account) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		String sql = "select *" + " from USER_TBL" + " where USER_ID = :USER_ID;";
		parameters.put("ACCOUNT", account);

		Map<String, Object> result = namedParameterjdbcTemplate.queryForMap(sql, parameters);

		UserToken user = new UserToken();
		user.setAccount(account);
		user.setUserId((String) result.get("USER_ID"));

		return Optional.ofNullable(user);

	}

	@Override
	@Transactional
	public void insertUser(RegistationForm user) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		String sql = "insert into USER_TBL " + "(ACCOUNT,PASS ) " + "values(:ACCOUNT,:PASS);";
		parameters.put("ACCOUNT", user.getAccount());
		parameters.put("PASS", user.getPass());

		
		namedParameterjdbcTemplate.update(sql, parameters);

	}

}
