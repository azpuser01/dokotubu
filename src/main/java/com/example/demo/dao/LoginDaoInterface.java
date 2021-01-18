package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.DokotubuConstant;



public interface LoginDaoInterface {
	Optional<DokotubuConstant> login(String userId, String password);
}
