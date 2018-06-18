<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="com.example.demo.domain.PageVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<script type="text/javascript">
function urlEncode(path,name){
	var text = urlencode(path)+urlencode(name)
	console.log(text)
	return text;
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>음원리스트</title>
<style type="text/css">

body { color:#FFFFFF; background-color:#262626; }
#div_main{
margin:auto; 
width:800px;
}

#div_top{
width:100%;
height:100px;
text-align:center;
background-color:#262626;
padding:20px;
}

#div_menu{
width:200px;
float:left;
height:100%;
text-align:left;
background-color:#262626;
padding:20px;
}

#div_con{
width:80%;
float:left;
height:100%;
text-align:left;
background-color:#262626;
padding:20px;
}

#div_bottom{
margin:auto;
width:100%;
height:100px;
background-color:#262626;
clear:both;
padding:20px;
}
#div_topmenu{
display: inline;
width:100%;
height:30px;
margin:5px;
}
.test{
display: flex;
}

#content{
with:100%;
margin:0 auto;
text-align:center;
display: inline;
padding:5px;
}
.p{
padding:20px;
}

.topmenu{
text-align:center;
display: inline;
pading : 5px 10px 5px 10px;
float: right; 
width: 20%;

}
</style>
</head>
<body>
<div id="div_top">
<img onclick="location.href='/music/login'" style="float :left;" src="http://localhost/1motape/logo1motape.jpg" width=200px; height=100px;></img>
<input type="text" placeholder="검색">검색
<div  class="p" style="float :right;"> 
<form action="/loginProc" name="login" method="post">
	<input type="text" id="userId" name="userId" placeholder="Id"><br>
	<input type="password" id="userPw" name="userPw" placeholder="Password">
	<br>
	
	<button type="submit" id="btns"  onclick="location.href='/loginProc'">로그인</button>
</form>
	<button type="submit" id="newuser-btns"  onclick="location.href='/newUser'">회원가입</button>

</div>
</div>
<div id="div_topmenu">
<div class="topmenu"><a>최신</a></div>
<div class="topmenu"><a>탑100</a></div>
<div class="topmenu"><a>수상</a></div>
<div class="topmenu"><a>이벤트</a></div>
</div>
<div class="test">
	<div id="div_menu"> 사이드메뉴
		<div>인기차트
		</div>
	</div>
	
<div id="div_con" >
	<div id="content"><h4>음원 리스트</h4></div>
	<table class="table table-hover">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>다운로드</th>
            <th>재생</th>
        </tr>
            <c:forEach var="l" items="${page}">
          	  <tr>
              	  <td>${l.music_no}</td>
                  <td>${l.ori_music_nm}</td>
                  <td>${l.up_nick_nm}</td>
                  <td> <fmt:formatDate value="${l.up_date}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
                  <td>
                  <form action="/music/down" style="float:left;" id="musicDown" name="musicDown" method="post">
                  <button type="submit" id="music_no" name="music_no" onclick="Location.href='/music/login/'" value="${l.music_no}">다운</p>
                  </form>
                  </td>
                  <td><audio controls><source src="http://localhost/1motape/${l.music_nm}" type="audio/mpeg" ></audio></td>
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

<div id="div_bottom">아래쪽</div>

</body>
</html>