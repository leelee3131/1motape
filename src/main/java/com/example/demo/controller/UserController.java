package com.example.demo.controller;



import java.util.Date;
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



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.comm.EmailSecure;
import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;

/**
 * 유저 컨트롤러*/
@Controller
public class UserController {
	
	@Resource(name = "com.example.demo.service.UserService")
	UserService userService;
	
	@RequestMapping("/test")
	public String test(Model model) throws Exception{
		
		return "hello";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request) throws Exception{
		UserVO userVO = new UserVO();
		userVO.setUser_id("jihoon");
		userVO = userService.newUser(userVO);
		
		System.out.println(userVO.getNick_nm());

		
		return "login";
	}
	@RequestMapping("/loginProc")
	public String loginProc(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		System.out.println("id"+userId+"pw"+userPw);
		
		return "main";
	}
	@RequestMapping("/newUser")
	public String newUser(HttpServletRequest request) throws Exception{
		
		return "newuser";
	}
	@RequestMapping("/newUserProc")
	public String newUserProc(HttpServletRequest request) throws Exception{
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
		String subject = "Mail Send Test!";
		String text = "메일 발송 테스트중!!!!";
		
		userVO.setUser_email_key(emailkey);
		userVO.setUser_id("jihoon");
		
		if(userVO.getUser_email_key() != null)
			userService.updateEmailKey(userVO);
		
		
		System.out.println(emailkey);
		
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setContent(text + " 인증키 32자리 입니다. 복사하여 인증해주세요. " + emailkey,"text/html;charset=UTF-8");
		message.setSentDate(new Date());
		Transport.send(message);
		return "main";
	}
	
}