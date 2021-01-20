package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.dto.UserToken;
import com.example.demo.form.RegistationForm;





public interface UserSettingDaoInterface {

	/**
	 * ユーザー情報の取得
	 * @param
	 * @return Optional<{@link UserTbl}>
	 */
	Optional<UserToken> getUser(String account);
	
	/**
	 * ユーザー情報新規作成
	 * @param user
	 * @param pass
	 */
	void insertUser(RegistationForm user);
}
