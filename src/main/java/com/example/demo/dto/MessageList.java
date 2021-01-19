package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Message_tbl;

/**
 * メッセージの検索条件をSQLクエリ用データに変換するDTO
 * 
 * @since 2021-01-18
 * @author ueno
 * @author demo
 */

public class MessageList extends Message_tbl {
	private List<String> messageList;
	private int messageId;

	public MessageList() {
		super();
	}

	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

}
