package com.example.demo.domain;

import lombok.Data;

@Data
public class PageVO {
	private int maxpost; //페이지당 표시될 게시물 최대갯수,현재 페이지 게시물 갯수
	private int firstpageno; //첫번째 페이지 번호
	private int prevpageno; // 이전 페이지 번호
	private int startpageno; //시작 페이지
	private int curpageno; //현재 페이지번호
	private int endpageno; //끝페이지 번호
	private int nextpageno; //다음 페이지번호
	private int finalpageno; //마지막 페이지 번호
	private int countall; // 전체 게시물 수
	private int pagesize; //보여지는 페이지 수
	
	public void makepage() {
		if(countall==0)
			return;
		if(curpageno==0)
			setCurpageno(1);
		if(maxpost==0)
			setMaxpost(10);
		int finalpage=(countall+(maxpost-1))/maxpost;
		if(curpageno>finalpage)
			setCurpageno(finalpage);
		if(curpageno<0)
			curpageno=1;
		boolean isNowfirst=curpageno==1?true:false;
		boolean isNowfinal=curpageno==finalpage?true:false;
		
		int startpage=((curpageno-1)/countall)*pagesize+1;
		int endpage=startpage+pagesize-1;
		
		if(endpage>finalpage)
			endpage=finalpage;
		setFirstpageno(1);
		
		if(!isNowfirst)
			setPrevpageno(((startpage-1)<1?1:(startpage-1)));
		setStartpageno(startpage);
		setEndpageno(endpage);
		
		if(!isNowfinal)
			setNextpageno(((endpage+1>finalpage?finalpage:(endpage+1))));
		setFinalpageno(finalpage);
		
	}
	public PageVO(int curpageno,int maxpost) {
		this.curpageno=curpageno;
		this.pagesize=10;//페이지당 10개씩
		this.maxpost=(maxpost!=0)?maxpost:10;
	}
}
