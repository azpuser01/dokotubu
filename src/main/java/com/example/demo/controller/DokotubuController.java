package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DokotubuConstant;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

@Controller
@SessionAttributes(types=LoginForm.class)
@RequestMapping
public class DokotubuController {

	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private LoginService loginService;

	@Autowired
	DokotubuController(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			LoginService loginService) {
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
		this.loginService = loginService;
	}

	@ModelAttribute("loginForm")
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@GetMapping("Main")//Mainでリダイレクトされた場合動作
	public String getMain(Model model) {
		return "main";//main.htmlへ遷移
	}
	
	@PostMapping("Login")//Loginでpostされた場合動作
	public String postLogin(@ModelAttribute("loginForm") @Validated LoginForm loginForm, Model model,RedirectAttributes redirectAttributes) {
		String userId = loginForm.getAccount();
		int password = loginForm.getPass();

		DokotubuConstant loginResult = loginService.login(userId, Integer.toString(password));
		
		if( loginResult.equals(DokotubuConstant.IS_APPROVAL)) {
			
			return "redirect:Main";//URLを変えるためMainでリダイレクト			
		}else {
			//失敗時
			redirectAttributes.addFlashAttribute("errmsg", "ログインできませんでした。");
			return "redirect:";
		}
	}
	
	
	@GetMapping("Logout")
	public String getLogout(@ModelAttribute("loginForm") @Validated LoginForm loginForm,SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "index";
	}
	
	@GetMapping("Top")
	public String getTop(Model model) {
		return "index";//main.htmlへ遷移
	}
}
