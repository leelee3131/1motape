<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Form</title>
<script src="//code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var i=1;
var txt="";
function addfilediv(){
	txt = "<div><input type='file' name='files"+i+"'></div>";
    document.getElementById("field").outerHTML += txt;
	i++;
}
function fileInsert(){
	var formData = new formData($("#insertForm")[0]);
	
	alert("asdasdasd");
    $.ajax({
        url : "/music/insertProc",
        type : "post",
		data : formData,
		processData : false,
		contentType : false,
		success : function(formData) {
			console.log("ajax success");
			console.log(formData);
			alert("성공");
			location.href = "/main"
		},
		error : function( _error ){
			console.log("ajax error");
			console.log(_error);
			
			alert("에러");
			location.href = "/main"
		}
    });
   
}
           
</script>
</head>
<body>
<h1 style="background-color:#FFA500;">개인정보</h1>
<div class="container">
    <form action="/music/insertProc" name="insertForm" method="post" enctype="multipart/form-data">
      <div id="filediv" style="display:none">
      <input type="file" id="file" name="files">
      </div>
      
      <input type="button" value="파일첨부" onclick="addfilediv()"><br>
      <div id="field">
      
      </div> 
      <button type="submit" name="btns" class="btn btn-primary" onclick="fileInsert()">업로드</button>
    </form>
</div>


</body>
</html>

