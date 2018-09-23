package com.pilgrimm.wm.common.user.mapper;

import java.util.List;

import com.pilgrimm.wm.common.user.model.User;

public interface UserMapper {
	
	boolean save(User user);
	
	boolean update(User user);
	
	boolean delete(int id);
	
	User findById(int id);  
	
    List<User> findAll();

	User findByUsername(String username); 

}
