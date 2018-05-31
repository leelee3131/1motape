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

이메일 인증입니다.
<form action="/newUserProc" name="newUser" method="post">
	
	<input type="text" id="email" name="email" placeholder="email">
	
	<button type="submit" name="btns" class="btn btn-primary" onclick="Location.href='/newUserProc'">인증</button>
</form>
</body>
</html>

    