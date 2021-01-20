package com.example.demo.entity;

public class User_tbl {

	// フィールド
	private String account;
	private String pass;
	private int userId;

	// コンストラクタ
	public User_tbl() {
	}

	// getter setterの作成
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
