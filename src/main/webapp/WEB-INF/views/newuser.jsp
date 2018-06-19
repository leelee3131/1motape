<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<script src="//code.jquery.com/jquery.js"></script>
<script type="text/javascript">


function emailSecure(){
	var email = document.getElementById("email").value;
	console.log(email)
	alert(email+"로 인증메일이 발송되었습니다.")
	$.ajax({
		url : "/emailProc",
		type : "post",
		data : {"email" : email },
		success : function(){
			alert("성공")
		}
	})
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<style type="text/css">

body { color:#FFFFFF; background-color:#262626; }

#div_main{
text-align:left;
margin:auto; 
width:800px;
padding:20px;

}

#div_top{
width:100%;
height:100px;
text-align:center;
background-color:#262626;
padding:20px;
}

.email_class{
width:100%;
text-align:left;
background-color:#262626;
padding :20px 0px 20px 0px;
}
</style>
</head>
<body>
<div id="div_top">
<img onclick="location.href='/music/login'" style="float :left;" src="http://localhost/1motape/logo1motape.jpg" width=200px; height=100px;></img>
</div>
<hr color=#0101DF></hr>
<div id="div_main">
	회원가입 페이지입니다.
<form action="/newUserProc" name="newUser" method="post">
	<div class = "newUser-class">
	<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요"><br>
	<input type="password" id="userPw" name="userPw" placeholder="비밀번호 입력해주세요"><br>
	<input type="password" id="userPwok" name="userPwok" placeholder="비밀번호 다시입력해주세요"><br>
	<input type="text" id="nickNm" name="nickNm" placeholder="닉네임을 입력해주세요"><br>
	</div>
	<div class = "email_class">
	
	<input type="text" id="email" name="email" placeholder="email 입력해주세요">
	<input type="button" id="email-secure-form" name="email-secure-form" onclick="emailSecure()" value="인증"></button><br>
	
	<input type="text" id="emailKey" name="emailKey" placeholder="email 인증키 입력해주세요">
	</div>
	<button type="submit" name="btns" class="btn btn-primary" onclick="location.href='/newUserProc'">완료</button>
	</form>
	<button name="btns" class="btn btn-primary" onclick="location.href='/music/login'">취소</button>
	
</div>

</body>
</html>

    