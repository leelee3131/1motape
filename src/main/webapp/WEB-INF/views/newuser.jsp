<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
 
</head>
<body>

<h1 style="background-color:#FFA500;">login</h1>

이메일 인증입니다.
<form action="/newUserProc" name="newUser" method="post">
	<div class = "newUser-class">
	<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요"><br>
	<input type="text" id="userPw" name="userPw" placeholder="비밀번호 입력해주세요"><br>
	<input type="text" id="userPwok" name="userPwok" placeholder="비밀번호 다시입력해주세요"><br>
	<input type="text" id="userNm" name="userNm" placeholder="닉네임을 입력해주세요"><br>
	</div>
	<div class = "email-class">
	<input type="text" id="email" name="email" placeholder="email 입력해주세요">
	<input type="button" id="email-secure-form" name="email-secure-form" onclick="emailSecure()" value="인증"><br>
	<input type="text" id="emailKey" name="emailKey" placeholder="email 인증키 입력해주세요">
	</div>
	
	<button type="submit" name="btns" class="btn btn-primary" onclick="location.href='/newUserProc'">완료</button>
</form>
</body>
</html>

    