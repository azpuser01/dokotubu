package com.example.demo.dto;

import com.example.demo.entity.Message_tbl;

/**
 * メッセージテーブルを継承し、ユーザーマスタを結合したデータ保持用Entityクラス
 * @since 2021-01-18
 * @author ueno
 * @author demo
 */


public class ExtendedMessage  extends Message_tbl{
	private String accunt;
	private String message;
	private int logId;
	private int MessageId;
	
	
	
	public ExtendedMessage() {
		super();
	}
	
	
	public String getAccunt() {
		return accunt;
	}
	public void setAccunt(String accunt) {
		this.accunt = accunt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getMessageId() {
		return MessageId;
	}
	public void setMessageId(int messageId) {
		MessageId = messageId;
	}
	
	

}
