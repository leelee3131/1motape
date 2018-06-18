<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.demo.domain.PageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>음원리스트</title>
<h1 style="background-color:#FFA500;">음원리스트</h1>
</head>
<body>
<div>
<h1>음원</h1>
<div class="container" style="float:right;" >
	<button class="btn btn-primary" style="float :right;"  onclick="location.href='/music/insert'">음원 업로드</button>
</div><br>
<div class="container" style="width: 900px;">
    <table class="table table-hover">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>다운로드</th>
        </tr>
            <c:forEach var="l" items="${page}">
          	  <tr>
              	  <td>${l.music_no}</td>
                  <td>${l.ori_music_nm}</td>
                  <td>${l.up_nick_nm}</td>
                  <td> <fmt:formatDate value="${l.up_date}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                  <td>
                  <form action="/music/down" style="float:left;" id="musicDown" name="musicDown" method="post">
                  <button type="submit" id="music_no" name="music_no" onclick="Location.href='/music/list/'" value="${l.music_no}">다운로드</button>
                  </form>
                  </td>
              </tr>
          </c:forEach>
    </table>
	
	<div class="container" style="display: inline-block;">
<% PageVO paging=(PageVO)request.getAttribute("paging");
	int curpage=paging.getCurpageno();
	int startpage=(curpage-1)/10+1;
	int endpage=(startpage)*10;
	int finalpage=paging.getFinalpageno();
	if(endpage>finalpage)
		endpage=finalpage;	%>
<%if(startpage>1){%>
	<form action="/music/list" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-primary center-block" type="submit" id="pages" name="pages" onclick="Location.href='/music/list/'" value=<%=1%>>첫페이지로</button>
	</form>
<%} %>
<%if(startpage>1){%>
	<form action="/music/list" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-primary center-block" type="submit" id="pages" name="pages" onclick="Location.href='/music/list/'" value=<%=(startpage-2)*10+1%>>이전</button>
	</form>
<%} %>
<%for(int i=(startpage-1)*10+1;i<=endpage;i++){ %>
	<form action="/music/list" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-primary center-block" type="submit" id="pages" name="pages" onclick="Location.href='/music/list/'" value=<%=i%>><%=i %></button>
	</form>
<%} %>
<%if(endpage<finalpage){%>
	<form action="/music/list" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-primary center-block" type="submit" id="pages" name="pages" onclick="Location.href='/music/list/'" value=<%=(startpage)*10+1%>>다음</button>
	</form>
<%} %>
<%if(endpage<finalpage){%>
	<form action="/music/list" style="float:left;" id="paging" name="paging" method="post">
	<button class="btn btn-primary center-block" type="submit" id="pages" name="pages" onclick="Location.href='/music/list/'" value=<%=finalpage%>>끝페이지로</button>
	</form>
<%} %>
	
	<br>
	
	</div>
</div>
</div>


</body>
</html>

    