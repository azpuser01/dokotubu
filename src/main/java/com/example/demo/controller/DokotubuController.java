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
import com.example.demo.dto.UserToken;
import com.example.demo.entity.Message_tbl;
import com.example.demo.entity.User_tbl;
import com.example.demo.form.LoginForm;
import com.example.demo.form.MainForm;
import com.example.demo.form.RegistationForm;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserSettingService;
import com.example.demo.service.UserSettingServiceInterface;

@Controller
@SessionAttributes(types = UserToken.class)
@RequestMapping
public class DokotubuController {

	private LoginService loginService;
	private MessageDao messageDao;
	private UserSettingService userSettingService;
	private UserToken userToken;
	
	@Autowired
	public DokotubuController(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,LoginService loginService, MessageDao messageDao, UserSettingService userSettingService,UserToken userToken) {
		super();
		this.loginService = loginService;
		this.messageDao = messageDao;
		this.userSettingService = userSettingService;
		this.userToken = userToken;
	}


//	@Autowired
//	DokotubuController(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//			LoginService loginService,MessageDao messageDao) {
//		this.loginService = loginService;
//		this.messageDao = messageDao;
//	}

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
	public String postLogin(@ModelAttribute("loginForm") @Validated LoginForm loginForm, BindingResult result,Model model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("errmsg", "アカウント名とパスワードを入力してください。");
			return "redirect:";
		}
		String account = loginForm.getAccount();
		String pass = loginForm.getPass();
		DokotubuConstant loginResult = loginService.login(account, pass);

		if (loginResult.equals(DokotubuConstant.IS_APPROVAL)) {
			userToken = loginService.getUserToken(account);
			System.out.println(userToken.getUserId());
			redirectAttributes.addFlashAttribute(userToken);
			return "redirect:Main";// URLを変えるためMainでリダイレクト
		} else {
			// 失敗時
			redirectAttributes.addFlashAttribute("errmsg", "ログインできませんでした。");
			return "redirect:";
		}
	}

	@PostMapping("PostMessage") // PostMessageでpostされた場合動作
	public String postMessage(@ModelAttribute("mainForm") @Validated MainForm mainForm,BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		Message_tbl messageTbl = new Message_tbl();
				
		if (result.hasErrors() || mainForm.getMessage().length() == 0) {
			redirectAttributes.addFlashAttribute("errmsg", "投稿内容がありません。");
			return "redirect:Main";
		}
		System.out.println("[Form]"+mainForm.getMessage() + mainForm.getUserId());

		System.out.println(userToken.getUserId());
		System.out.println(mainForm.getMessage());
		System.out.println(userToken.getUserId());
		int userId = Integer.parseInt(mainForm.getUserId()) ;
		messageTbl.setUserId(userId);
		messageTbl.setMessage(mainForm.getMessage());
		messageDao.insertMessage(messageTbl);
		
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
	
	@GetMapping("Registation")
	public String GetRegistation() {
		
		return "registation";
	}
		
	@PostMapping("Registation")
	public String PostRegistation(@Validated RegistationForm registationForm,Model mode,RedirectAttributes redirectAttributes) {
		User_tbl userTbl = new User_tbl();
		userTbl.setAccount(registationForm.getAccount());
		userTbl.setPass(registationForm.getPass());
		
		redirectAttributes.addFlashAttribute("title", "ユーザー登録完了");
		
		userSettingService.addUser(registationForm);
		
		return "index";
		
	}
}
