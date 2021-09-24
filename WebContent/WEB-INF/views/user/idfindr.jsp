<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<style>
.container {
	width: 385px;
	line-height: 50px;
	margin: 40px auto;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.login {
	background-color: gray;
	color: white;
	border-radius: 5px;
	border: 0;
	padding: 3px 100px;
}

#signup {
	background-color: white;
	color: teal;
	border: 0;
	font-size: 17px;
}

p {
	text-align: center;
}

i {
	color: lightgray;
}

input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../menu/top.jsp" %>
<div class="container">
	<h5><span>아이디찾기</span> 페이지입니다.</h5>
	<p align="center">회원가입시 사용한 아이디는 <b><%=request.getAttribute("id") %></b>입니다!</p>
	<p><a href="login.do"><input type="button" value="로그인"  id="login" ></a></p>
     <p><a href="pwfind.do"><input type="button" value="비밀번호찾기"  id="signup"></a></p>
</div>
</body>
</html>