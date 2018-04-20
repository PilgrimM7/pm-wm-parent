package com.pilgrimm.wm.common.user.dao.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.pilgrimm.core.common.AbstractDao;
import com.pilgrimm.core.common.DefaultPageResultGenerator;
import com.pilgrimm.core.common.MysqlPageSqlEntry;
import com.pilgrimm.core.util.ParamUtil;
import com.pilgrimm.wm.common.user.dao.UserDao;
import com.pilgrimm.wm.common.user.model.User;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

	public void update(Map<String, Object> paramMap) {
		String sql = "update t_user set name = '" + paramMap.get("name") + "' where id = 1";
		update(sql, paramMap);
	}
	
	public Map<String, Object> queryForPage(Map<String, Object> paramMap) {
		String name = ParamUtil.getString(paramMap, "name");
		
		String sql = "SELECT * FROM t_user WHERE 1=1";
		if (StringUtils.isNotEmpty(name)) {
			sql += " AND name LIKE '%" + name + "%'";
		}
		return queryForPageMap(new MysqlPageSqlEntry(sql), paramMap, 
				new DefaultPageResultGenerator<User>(this));
	}
	
}
