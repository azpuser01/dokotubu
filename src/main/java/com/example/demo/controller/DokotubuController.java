package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DokotubuConstant;
import com.example.demo.dao.MessageDao;
import com.example.demo.dto.ExtendedMessage;
import com.example.demo.dto.MessageList;
import com.example.demo.form.LoginForm;
import com.example.demo.form.MainForm;
import com.example.demo.service.LoginService;

@Controller
@SessionAttributes(types = LoginForm.class)
@RequestMapping
public class DokotubuController {

	private LoginService loginService;
	private MessageDao messageDao;

	@Autowired
	DokotubuController(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			LoginService loginService,MessageDao messageDao) {
		this.loginService = loginService;
		this.messageDao = messageDao;
	}

	@ModelAttribute("loginForm")
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@GetMapping("Main") // Mainでgetされた場合動作
	public String getMain(Model model, MessageList messageList) {
		List<ExtendedMessage> msgList = messageDao.getAllMessage(messageList);
		model.addAttribute("msgList", msgList);
		
//		for(ExtendedMessage e:msgList) {
//			System.out.println(e.getAccount());
//		}
		
		return "main";// main.htmlへ遷移
	}

	@PostMapping("Login") // Loginでpostされた場合動作
	public String postLogin(@ModelAttribute("loginForm") @Validated LoginForm loginForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errmsg", "アカウント名とパスワードを入力してください。");
			return "redirect:";
		}
		String account = loginForm.getAccount();
		String password = Integer.toString(loginForm.getPass());
		DokotubuConstant loginResult = loginService.login(account, password);

		if (loginResult.equals(DokotubuConstant.IS_APPROVAL)) {
			return "redirect:Main";// URLを変えるためMainでリダイレクト
		} else {
			// 失敗時
			redirectAttributes.addFlashAttribute("errmsg", "ログインできませんでした。");
			return "redirect:";
		}
	}

	@PostMapping("PostMessage") // PostMessageでpostされた場合動作
	public String postMessage(LoginForm loginForm, @ModelAttribute("mainForm") @Validated MainForm mainForm,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors() || mainForm.getMessage().length() == 0) {
			redirectAttributes.addFlashAttribute("errmsg", "投稿内容がありません。");
			return "redirect:Main";
		}
		String account = mainForm.getAccount();
		String message = mainForm.getMessage();
		DokotubuConstant postMessageResult = loginService.login(account, message);

		if (postMessageResult.equals(DokotubuConstant.IS_APPROVAL)) {
		} else {
			// 失敗時
			redirectAttributes.addFlashAttribute("errmsg", "投稿できませんでした。");
		}
		return "redirect:Main";
	}

	@GetMapping("Logout")
	public String getLogout(@ModelAttribute("loginForm") @Validated LoginForm loginForm, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "index";
	}

	@GetMapping("Top")
	public String getTop(Model model) {
		return "index";// main.htmlへ遷移

	}
}
