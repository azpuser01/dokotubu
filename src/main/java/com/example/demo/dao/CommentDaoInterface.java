package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.ExtendedMessage;
import com.example.demo.dto.MessageList;
import com.example.demo.entity.Message_tbl;

/**
 * dokotubuつぶやき関連のクエリを管理するDAOクラスのインターフェイス
 * @since 2021-01-18
 * @author ueno
 * @author demo
 */

public interface CommentDaoInterface {
	/**
	 * つぶやき新規作成
	 * @param messageCreateForm
	 */
	void insertCallmemo(Message_tbl message);
	
	/**
	 * 表示条件を指定してコールメモリストを取得
	 * @param callmemoListForm
	 * @return List<{@link CallmemoList}>
	 */
	List<ExtendedMessage> getAllCallmemo(MessageList messageList);
	

}
