package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DokotubuController {

	@GetMapping("Main")//Mainでリダイレクトされた場合動作
	public String getMain(Model model) {
		return "main";//main.htmlへ遷移
	}
	
	@PostMapping("Login")//Loginでpostされた場合動作
	public String postLogin(Model model) {
		return "redirect:Main";//URLを変えるためMainでリダイレクト
	}
}
