package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserSettingDao;
import com.example.demo.form.RegistationForm;

@Service
public class UserSettingService implements UserSettingServiceInterface {
	private UserSettingDao dao;
	private PasswordEncode passwordEncode;
	
	
	@Autowired
	public UserSettingService(UserSettingDao dao, PasswordEncode passwordEncode) {
		super();
		this.dao = dao;
		this.passwordEncode = passwordEncode;
	}



	@Override
	public void addUser(RegistationForm registationForm) {
		try {
			
			registationForm.setPass(passwordEncode.makePasswordEncord(registationForm.getPass(), registationForm.getAccount()));
			dao.insertUser(registationForm);
		} catch (Exception e) {
			
			
		}

	}

}
