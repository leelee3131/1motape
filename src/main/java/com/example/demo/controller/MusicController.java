package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MusicService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MusicController {
	
	@Resource(name = "com.example.demo.service.MusicService")
	MusicService musicService;
	
	@RequestMapping("/music")
	public void insertMusic(HttpServletRequest request) throws Exception{
		
	}

}
