package com.example.demo.domain;

import java.util.Date;

import lombok.Data; 

@Data
public class MusicVO {
	
	int music_no;
	//음원 번호
	String music_nm;
	//음원 이름
	String music_code;
	//음원 코드
	int music_like;
	//음원 좋아요 갯수
	int music_play;
	//재생횟수
	String up_user_id;
	//업로드 id
	Date up_date;
	//업로드 날짜
	String ch_user_id;
	//수정자 id
	Date ch_date;
	//수정 날짜
	String up_nick_nm;
	//업로드 닉네임
	int music_like_pre;
	//지난달까지 조아요 갯수
	int music_accu;
	//신고 횟수
	
	
}
