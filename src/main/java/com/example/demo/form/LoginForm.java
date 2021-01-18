package com.example.demo.form;

public class LoginForm {

	
	// フィールド
	private String account;
	private int pass;

	// コンストラクタ
	public LoginForm() {
	}

	// getter setterの作成
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

}
