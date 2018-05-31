package com.example.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.domain.UserVO;
import com.example.demo.mapper.UserMapper;

/**
 * 유저 서비스
 * */
@Service("com.example.demo.service.UserService")
public class UserService {
	
	@Resource(name = "com.example.demo.mapper.UserMapper")
	UserMapper userMapper;
	
	public void updateEmailKey(UserVO userVO) throws Exception{
		userMapper.updateEmailKey(userVO);
	}
	
	public UserVO newUser(UserVO userVO) throws Exception{
		return userMapper.selectUser(userVO);
	}
	

}
