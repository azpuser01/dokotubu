package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DokotubuConstant;
import com.example.demo.dao.LoginDao;
import com.example.demo.dto.UserToken;

@Service
public class LoginService implements LoginServiceInterface {


	private LoginDao loginDao;


	

	@Autowired
	public LoginService(LoginDao loginDao) {

		this.loginDao = loginDao;
	}

	@Override
	public DokotubuConstant login(String account, String password) {
		try {
			
			loginDao.login(account, password).get();

//			userId.equalsIgnoreCase("minato");
//			userId.equalsIgnoreCase("1234");
		} catch (NullPointerException e) {
			return DokotubuConstant.IS_NOT_APPROVAL;
		}

		return DokotubuConstant.IS_APPROVAL;
	}

	public UserToken getUserToken(String account, String password) {
		
		UserToken userToken = null;
		try {
			
			userToken = loginDao.getUserToken(account, password);

//			userId.equalsIgnoreCase("minato");
//			userId.equalsIgnoreCase("1234");
		} catch (NullPointerException e) {
			return userToken;
		}

		return userToken;
	}

}
