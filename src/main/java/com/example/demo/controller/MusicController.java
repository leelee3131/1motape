package com.example.demo.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.domain.MusicVO;
import com.example.demo.service.MusicService;

import java.io.File;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/music")
public class MusicController {
	
	@Resource(name = "com.example.demo.service.MusicService")
	MusicService musicService;
	
	
	@RequestMapping("/insert")
	private String MusicInsert() throws Exception{
		
		return "insert";
	}
	@RequestMapping("/insertProc")
	private String MusicInsertProc(MultipartHttpServletRequest request) throws Exception{
		
		MusicVO musicVO = new MusicVO();
		Iterator<String> files=request.getFileNames();
		
		while(files.hasNext()) {
			String uploadFile=files.next();
			
		
			MultipartFile mFile=request.getFile(uploadFile);
			String fileName=mFile.getOriginalFilename();
			if(mFile.getSize()==0)
				continue;
			System.out.println("filename---->"+fileName);
			String changName=RandomStringUtils.randomAlphanumeric(32)+"_"+fileName;
			String fileUrl="C:\\Users\\leejihoon\\Downloads\\filetest\\";
			try {
				mFile.transferTo(new File(fileUrl+changName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			musicVO.setMusic_code("003001");
			musicVO.setMusic_nm(changName);
			musicVO.setUp_nick_nm("jihoon");
			musicVO.setUp_user_id("leelee31");
			
			
		
		}
		return "main";
		
	}
}
