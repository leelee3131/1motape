package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MusicVO;

@Repository("com.example.demo.mapper.MusicMapper")
public interface MusicMapper {
	public void insertMusic(MusicVO musicVO) throws Exception;
	public List<MusicVO> listMusic(MusicVO musicVO) throws Exception;
	public int musicCount(MusicVO musicVO) throws Exception;
	public MusicVO musicDetail(MusicVO musicVO) throws Exception;
}
