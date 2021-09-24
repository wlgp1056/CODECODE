<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>join</title>
<style>
        .container {
            width: 500px;
            margin: 40px auto;
            line-height: 16px;
        }
        h5 {
            text-align: center;
        }
        h5 span {
            color: teal;
        }
        .n {
            font-size: 13px;
        }
        #signup {
            background-color: gray;
            color: white;
            border: 0;
            border-radius: 5px;
            padding: 3px 100px;
            width=
        }
        .bottom input {
            background-color: white;
            border: 0;
            color: teal;
            font-size: 16px;
        }
        i {
            color: lightgray;
        }
       
        input {
            border: 1px solid lightgray;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<%@ include file="../menu/top.jsp" %>
<c:if test="${ joinr == 0}">
			<script>
			alert("아이디가 중복됩니다.");
			</script>
</c:if>
<div class="container">
        <h5><span>회원 가입</span> 페이지입니다.</h5>
        <hr /><br />
	<form method="post" action="" align="center">
  		<input type="text" placeholder="아이디" name="userID" checked required style="height:30px; width: 495px"/><br /><br />
  		<input type="password" placeholder="비밀번호" name="password" checked required style="height:30px; width: 495px"/><br /><br />
  		<input type="text" placeholder="이름" name="name" checked  required style="height:30px; width: 495px"/><br /><br />
  		<input type="radio" name="gender" autocomplete="off" value="남자" checked>남자
  		<input type="radio" name="gender" autocomplete="off" value="여자" checked>여자
  		<br /><br />
  		<input type="email" placeholder="이메일 주소" name="email" required style="height:30px; width: 495px" /><br /><br />
  		<p>
   		<input type="submit" value="가입하기" id="signup" required style="height:50px; width: 300px"/><br /><br />
  		</p>
	</form>
	<hr />
	</div>
</body>
</html>