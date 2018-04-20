package com.pilgrimm.wm.common.user.service;

import java.util.List;
import java.util.Map;

import com.pilgrimm.wm.common.user.model.User;

public interface UserService {

	void save(User user);
	
	boolean update(User user);
	
	boolean delete(int id);
	
	User findById(int id);  
	
    List<User> findAll();
    
    void txTest(User user1, User user2);
    
    Map<String, Object> queryForPage(Map<String, Object> paramMap);
}
