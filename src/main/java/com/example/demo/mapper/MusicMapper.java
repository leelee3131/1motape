package com.example.demo.mapper;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MusicVO;

@Repository("com.example.demo.mapper.MusicMapper")
public interface MusicMapper {
	public void insertMusic(MusicVO musicVO) throws Exception;
}
