package com.pilgrimm.wm.common.user.dao;

import java.util.Map;

public interface UserDao {

	Map<String, Object> queryForPage(Map<String, Object> paramMap);
	
}
