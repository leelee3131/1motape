package com.example.demo.mapper;


import org.springframework.stereotype.Repository;

import com.example.demo.domain.UserVO;

/**
 * 유저 매퍼 인터페이스*/
@Repository("com.example.demo.mapper.UserMapper")
public interface UserMapper {
	
	public void newUser(UserVO userVO) throws Exception;
	public UserVO selectUser(UserVO userVO) throws Exception;
	public void updateEmailKey(UserVO userVO) throws Exception;
	public UserVO login(UserVO userVO) throws Exception;
}
