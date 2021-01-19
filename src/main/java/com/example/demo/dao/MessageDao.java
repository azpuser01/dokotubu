package com.example.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ExtendedMessage;
import com.example.demo.dto.MessageList;
import com.example.demo.entity.Message_tbl;

@Repository
public class MessageDao implements MessageDaoInterface {

	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;

	@Autowired
	public MessageDao(NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
		this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
	}

	@Override
	public void insertMessage(Message_tbl message) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		// メッセージテーブルへのインサート
		String sql = "insert into MESSAGE_TBL" 
				+ "(MESSAGE, LOG_ID)" 
				+ "values(:MESSAGE,:LOG_ID)";

		
		parameters.put("LOG_ID", message.getUserId());
		parameters.put("MESSAGE", message.getMessage());

		namedParameterjdbcTemplate.update(sql, parameters);
	}

	@Override
	public List<ExtendedMessage> getAllMessage(MessageList messageList) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		String sql = "select U.* ,M.*" 
				+ "from USER_TBL; " 
				+ "left outer join MESSAGE_TBL　as U"
				+ "on U.USERID = M.LOG_ID" 
				+ "order by M.MESSAGEID";

		List<Map<String, Object>> resultList = namedParameterjdbcTemplate.queryForList(sql, parameters);
		List<ExtendedMessage> list = new ArrayList<ExtendedMessage>();
		
		for(Map<String, Object> result : resultList) {
			ExtendedMessage message = new ExtendedMessage();
			message.setAccount((String) result.get("account"));
			message.setMessage((String) result.get("message"));

			list.add(message);
		}

		return list;
	}


}
