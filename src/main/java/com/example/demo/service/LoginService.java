package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DokotubuConstant;
import com.example.demo.dao.LoginDaoInterface;

@Service
public class LoginService implements LoginServiceInterface {
	private HttpServletRequest httpServletRequest;
	private LoginDaoInterface loginDaoInterface;

	@Autowired
	public LoginService(HttpServletRequest httpServletRequest, LoginDaoInterface loginDaoInterface) {
		this.httpServletRequest = httpServletRequest;
		this.loginDaoInterface = loginDaoInterface;
	}

	@Override
	public DokotubuConstant login(String userId, String password) {
		try {

			loginDaoInterface.login(userId, password).get();

		} catch (NullPointerException e) {
			return DokotubuConstant.IS_NOT_APPROVAL;
		}

		return DokotubuConstant.IS_APPROVAL;
	}

}
