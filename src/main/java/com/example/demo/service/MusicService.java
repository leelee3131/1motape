package com.example.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MusicVO;
import com.example.demo.mapper.MusicMapper;

@Service("com.example.demo.service.MusicService")
public class MusicService {
	
	@Resource(name = "com.example.demo.domain.MusicMapper")
	MusicMapper musicMapper;
	
	
	

}
