package com.example.demo.comm;

import java.util.Random;

public class EmailSecure {
	/**
	 * 이메일 인증키 생성
	 * 랜덤 스트링 32자*/
	public String createEmailKey() throws Exception{
		
		StringBuffer sb= new StringBuffer();
		int size = 32;
		Random random = new Random();
		int num = 0;
		while(sb.length() < size){
			num = random.nextInt(75)+48;
			if((num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		} 
		
		
		return sb.toString();
	}
	

}
