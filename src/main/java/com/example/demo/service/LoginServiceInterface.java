package com.example.demo.service;

import com.example.demo.DokotubuConstant;

/**
 * ログイン機能に関わるServiceクラス用のインターフェイス
 * @since 2021-01-19
 * @author ueno
 * @author demo
 */

public interface LoginServiceInterface {
	
	/**
	 * ユーザー認証を行う
	 * @param
	 * @return {@link dokotubuConstant}
	 */
	DokotubuConstant login(String userId, String password);

}
