package com.example.demo.controller;



import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.comm.EmailSecure;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

/**
 * 유저 컨트롤러*/
@Controller
public class UserController {
	
	@Resource(name = "com.example.demo.service.UserService")
	UserService userService;
	
	
	@RequestMapping("/main")
	public String test(Model model) throws Exception{
		
		return "main";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request) throws Exception{
		
		//userVO = userService.newUser(userVO)
		return "login";
	}
	/**
	 * 로그인시 세션에 id,nickname저장*/
	@RequestMapping("/loginProc")
	public String loginProc(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserVO userVO = new UserVO();
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		userVO.setUser_id(userId);
		String oriUserPw = userService.login(userVO).getUser_pw().toString();
		String userNm = userService.login(userVO).getNick_nm().toString();
		//System.out.println("-----"+userPw);
		userVO.setUser_id(userId);
		userVO.setUser_pw(userPw);
		
		if(!userPw.equals(null)&&oriUserPw.equals(userPw)) {
//			System.out.println("-----"+oriUserPw);
//			System.out.println("-----"+userNm);
			session.setAttribute("userId", userId);
			session.setAttribute("userNm", userNm);
			
			return "redirect:/music/list";
		}
		else
			return "redirect:/music/login";
	}
	@RequestMapping("/newUser")
	public String newUser(HttpServletRequest request) throws Exception{
		
		return "newuser";
	}
	/**
	 * 메일 인증시에 session에 인증키 저장*/
	@RequestMapping("/emailProc")
	public void emailProc(HttpServletRequest request) throws Exception{
		UserVO userVO = new UserVO();
		
		EmailSecure emailsecure = new EmailSecure();
		String emailkey = emailsecure.createEmailKey();
		String port = "587";
		Properties props = new Properties();
		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");
		props.put("mail.smtp.auth", "true");
		
		props.put("mail.smtp.host", "smtp.naver.com");
		if (port != null)
		{
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		Message message = new MimeMessage(Session.getInstance(props, new Authenticator()
		{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("leelee3150@naver.com", "ghfla3150");
			}
		}));
		String from = "leelee3150@naver.com";
		String to = request.getParameter("email");
		String subject = "1motape 인증 메일입니다.!";
		String text = "1motape 메일 인증입니다.\n";
		
		userVO.setUser_email_key(emailkey);
		HttpSession session = request.getSession();
		session.setAttribute("emailkey", emailkey);
	
		
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(text + " 인증키 32자리 입니다. 복사하여 인증해주세요. " + emailkey,"text/html;charset=UTF-8");
		message.setSentDate(new Date());
		Transport.send(message);
		
	}
	
	@RequestMapping("/newUserProc")
	public String newUserProc(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		
		session.setAttribute("userId", request.getParameter("userId").toString());
		session.setAttribute("nickNm", request.getParameter("nickNm").toString());
		
		UserVO userVO = new UserVO();
		userVO.setUser_code("001002");
		userVO.setUser_id(request.getParameter("userId").toString());
		userVO.setUser_pw(request.getParameter("userPw").toString());
		userVO.setEmail(request.getParameter("email").toString());
		userVO.setUser_email_key(request.getParameter("emailKey").toString());
		userVO.setNick_nm(request.getParameter("userNm").toString());
		
		userService.newUser(userVO);
		
		return "main";
	}
}
