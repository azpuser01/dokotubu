package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DokotubuConstant;
import com.example.demo.dao.LoginDao;
import com.example.demo.dto.UserToken;

@Service
public class LoginService implements LoginServiceInterface {


	private LoginDao loginDao;
	private PasswordEncode passwordEncode;

	

	@Autowired
	public LoginService(LoginDao loginDao,PasswordEncode passwordEncode) {

		this.loginDao = loginDao;
		this.passwordEncode = passwordEncode;
	}

	@Override
	public DokotubuConstant login(String account, String password) {
		try {
			

			loginDao.login(account, passwordEncode.makePasswordEncord(password,account)).get();
//			userId.equalsIgnoreCase("minato");
//			userId.equalsIgnoreCase("1234");
		} catch (NullPointerException e) {
			return DokotubuConstant.IS_NOT_APPROVAL;
		}

		return DokotubuConstant.IS_APPROVAL;
	}

	public UserToken getUserToken(String account) {
		
		UserToken userToken = null;
		try {

//			loginDao.login(account, passwordEncode.makePasswordEncord(password,account)).get();
			userToken = loginDao.getUserToken(account);
			System.out.println(userToken.getUserId());

//			userId.equalsIgnoreCase("minato");
//			userId.equalsIgnoreCase("1234");
		} catch (NullPointerException e) {
			return userToken;
		}

		return userToken;
	}

}
