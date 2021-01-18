package com.example.demo.entity;

public class User_tbl {

	// フィールド
	private String account;
	private int pass;
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

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
