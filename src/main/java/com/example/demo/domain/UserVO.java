package com.example.demo.domain;


import lombok.Data;

/**
 * 유저 VO*/
@Data
public class UserVO { //사용자 테이블

	String email;
	// 회원 이메일
	String ph_number;
	// 회원 핸드폰 번호
	String nick_nm;
	// 회원 닉네임
	String user_id;
	// 회원 아이디
	String user_pw;
	// 회원 비밀번호
	String user_code;
	// 회원 상태 코드
	String user_img;
	// 회원 이미지
	String user_email_key;
	// 회원 이메일 인증키
}
