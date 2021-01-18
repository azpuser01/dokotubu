package com.example.demo.entity;

public class Message_tbl {

	// フィールド
	private String message;
	private int userId;
	private int messageId;

	// コンストラクタ
	public Message_tbl() {
	}

	// getter setterの作成
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

}
