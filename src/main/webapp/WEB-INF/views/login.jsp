<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
 
</head>
<body>

<h1 style="background-color:#FFA500;">login</h1>

<form action="/loginProc" name="login" method="post">
	<input type="text" id="userId" name="userId" placeholder="Id"><br>
	<input type="password" id="userPw" name="userPw" placeholder="Password">
	
	<button type="submit" name="btns" class="btn btn-primary" onclick="Location.href='/loginProc'">로그인</button>
</form>
	<button type="submit" name="btns" class="btn btn-primary" onclick="Location.href='/newUser'">회원가입</button>
	<button type="submit" name="btns" class="btn btn-primary" onclick="Location.href='/findId'">아이디 찾기</button>
	<button type="submit" name="btns" class="btn btn-primary" onclick="Location.href='/findPw'">비밀번호 찾기</button>
</body>
</html>

    