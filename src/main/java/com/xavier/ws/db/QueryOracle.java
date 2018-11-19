package com.xavier.ws.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class QueryOracle {

	@Autowired
	private JdbcTemplate dao;

	public List<Map<String, Object>> test() {
		String sql = "SELECT * FROM TAB1010209";
		List<Map<String, Object>> mapList = dao.queryForList(sql);
		for (Map<String, Object> it : mapList) {
			System.out.println(it);
		}
		return mapList;
	}
}
