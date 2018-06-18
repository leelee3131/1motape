package com.example.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MusicVO;
import com.example.demo.mapper.MusicMapper;


@Service("com.example.demo.service.MusicService")
public class MusicService {
	
	@Resource(name = "com.example.demo.mapper.MusicMapper")
	MusicMapper musicMapper;
	
	public void insertMusic(MusicVO musicVO) throws Exception{
		musicMapper.insertMusic(musicVO);
	}
	public List<MusicVO> listMusic(MusicVO musicVO) throws Exception{
		return musicMapper.listMusic(musicVO);
	}
	public int musicCount(MusicVO musicVO) throws Exception{
		return musicMapper.musicCount(musicVO);
	}
	public MusicVO musicDetail(MusicVO musicVO)throws Exception{
		return  musicMapper.musicDetail(musicVO);
	}
	

}
