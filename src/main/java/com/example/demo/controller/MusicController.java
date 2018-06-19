package com.example.demo.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.domain.MusicVO;
import com.example.demo.domain.PageVO;
import com.example.demo.service.MusicService;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/music")
public class MusicController {
	
	@Resource(name = "com.example.demo.service.MusicService")
	MusicService musicService;
	
	@RequestMapping("/down")
	private void fileDown(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("UTF-8");
		int musicNo = Integer.parseInt(request.getParameter("music_no"));
		MusicVO musicVO = new MusicVO();
		musicVO.setMusic_no(musicNo);
	
		musicVO=musicService.musicDetail(musicVO);
		System.out.println(musicVO.getMusic_no());
		try {
			String filePath=musicVO.getMusic_path();
			filePath += "/";
			String savePath=filePath;
			String fileName=musicVO.getMusic_nm();
			String oriFileName=musicVO.getOri_music_nm();
			
			InputStream in=null;
			OutputStream os=null;
			File filetest=null;
			boolean skip = false;
			String client="";
			
			try {
				filetest=new File(savePath,fileName);
				in = new FileInputStream(filetest);
			}catch(FileNotFoundException fe) {
				skip=true;
			}
			
			client=request.getHeader("User-Agent");
			
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");
			
			if(!skip) {
				if(client.indexOf("MSIE")!=-1) {
					response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
				}else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
				response.setHeader("Content-Length", "" + filetest.length());
				os=response.getOutputStream();
				byte b[] =new byte[(int)filetest.length()];
				int leng=0;
				while((leng=in.read(b))>0) {
					os.write(b,0,leng);
				}
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out =response.getWriter();
				out.println("<script langauage='javascript'>alert('파일을 찾을 수 없습니다');history.go(-1);</script>");
				out.flush();
			}
			in.close();
			os.close();
		}catch(Exception e) {
			System.out.println("ERROR:"+e.getMessage());
		}
	}
	@RequestMapping("/login")
	private String LogboardList(HttpServletRequest request,Model model)throws Exception { 
		int curpageno=1;
		int maxpost=10;
		if(request.getParameter("pages")!=null)
			curpageno=Integer.parseInt(request.getParameter("pages"));
		PageVO paging=new PageVO(curpageno,maxpost);
		
		int offset=(paging.getCurpageno()-1)*paging.getMaxpost();
		
		ArrayList<MusicVO> page=new ArrayList<MusicVO>();
		MusicVO musicVO=new MusicVO();
		musicVO.setOffset(offset);
		musicVO.setCount(paging.getMaxpost());
		musicVO.setMusic_code("003001");
		page.add(musicVO);
		page=(ArrayList<MusicVO>) musicService.listMusic(musicVO);
		paging.setCountall(musicService.musicCount(musicVO));
		
		paging.makepage();
		int i=0;
		while (i<10){
		    String name = page.get(i).getMusic_path() + page.get(i).getMusic_nm();
		    System.out.println("경로+이름----"+name);
		    i++;
		};
		
		
		model.addAttribute("page",page);
		model.addAttribute("paging",paging);
		
		return "login";
	}
	@RequestMapping("/list")
	private String boardList(HttpServletRequest request,Model model)throws Exception { 
		int curpageno=1;
		int maxpost=10;
		if(request.getParameter("pages")!=null)
			curpageno=Integer.parseInt(request.getParameter("pages"));
		PageVO paging=new PageVO(curpageno,maxpost);
		
		int offset=(paging.getCurpageno()-1)*paging.getMaxpost();
		
		ArrayList<MusicVO> page=new ArrayList<MusicVO>();
		MusicVO musicVO=new MusicVO();
		musicVO.setOffset(offset);
		musicVO.setCount(paging.getMaxpost());
		musicVO.setMusic_code("003001");
		page.add(musicVO);
		page=(ArrayList<MusicVO>) musicService.listMusic(musicVO);
		paging.setCountall(musicService.musicCount(musicVO));
		
		paging.makepage();
		
		
		
		model.addAttribute("page",page);
		model.addAttribute("paging",paging);
		
		return "musiclist";
	}
	@RequestMapping("/insert")
	private String MusicInsert() throws Exception{
		
		return "insert";
	}
	@RequestMapping("/insertProc")
	private String MusicInsertProc(MultipartHttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		Map<String,String> map = new HashMap();
		map = (Map<String, String>) session.getAttribute("userInfo");
		
		MusicVO musicVO = new MusicVO();
		Iterator<String> files=request.getFileNames();
		
		while(files.hasNext()) {
			String uploadFile=files.next();
			
		
			MultipartFile mFile=request.getFile(uploadFile);
			String fileName=mFile.getOriginalFilename();
			if(mFile.getSize()==0)
				continue;
			System.out.println("filename---->"+fileName);
			String[] extendStr = fileName.split("\\.");
			int count = extendStr.length;
			String extend = extendStr[count-1];
			String changName=RandomStringUtils.randomAlphanumeric(32)+"."+extend;
			String fileUrl="C:\\Users\\leejihoon\\Downloads\\filetest\\";
			try {
				mFile.transferTo(new File(fileUrl+changName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			musicVO.setMusic_path(fileUrl);
			musicVO.setOri_music_nm(fileName);
			musicVO.setMusic_code("003001");
			musicVO.setMusic_nm(changName);
			musicVO.setUp_nick_nm("지훈");
			musicVO.setUp_user_id(map.get("userId"));
			
			musicService.insertMusic(musicVO);
			
		
		}
		return "redirect:/music/list";
	}
}
