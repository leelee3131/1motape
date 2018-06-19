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
	alert(email)
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
text-align:center;
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

.email_class{
width:100%;
text-align:center;
background-color:#262626;
padding :30px;
}
</style>
</head>
<body>
<div id="div_top">
<img onclick="location.href='/music/login'" style="float :left;" src="http://localhost/1motape/logo1motape.jpg" width=200px; height=100px;></img>
</div>
<div id="div_main">
	이메일 인증입니다.
<form action="/newUserProc" name="newUser" method="post">
	<div class = "newUser-class">
	<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요"><br>
	<input type="text" id="userPw" name="userPw" placeholder="비밀번호 입력해주세요"><br>
	<input type="text" id="userPwok" name="userPwok" placeholder="비밀번호 다시입력해주세요"><br>
	<input type="text" id="nickNm" name="nickNm" placeholder="닉네임을 입력해주세요"><br>
	</div>
	<div class = "email_class">
	<input type="text" id="email" name="email" placeholder="email 입력해주세요">
	<input type="button" id="email-secure-form" name="email-secure-form" onclick="emailSecure()" value="인증"><br>
	<input type="text" id="emailKey" name="emailKey" placeholder="email 인증키 입력해주세요">
	</div>
	
	<button type="submit" name="btns" class="btn btn-primary" onclick="location.href='/newUserProc'">완료</button>
	<button name="btns" class="btn btn-primary" onclick="location.href='/music/login'">취소</button>
</form>
</div>

</body>
</html>

    