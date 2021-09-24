<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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

.find {
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

#imail {
	position: absolute;
	top: 130px;
	margin: 0 355px;
}

#ipw {
	position: absolute;
	top: 180px;
	margin: 0 355px;
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
<c:if test="${ idr == 0 }">
		<script>
			alert("아이디가 존재하지 않습니다.");
		</script>
</c:if>
<div class="container">
	<h5><span>아이디찾기</span> 페이지입니다.</h5>
	<p align="center">아이디는 가입시 입력하신 이메일을 통해 찾을 수 있습니다!</p>
	<hr />
	<form action="" method="post" align="center">
	 <input type="text" placeholder="이름" name="name" required style="height:30px; width: 380px" /><br />
	 <input type="text" placeholder="이메일" name="email" required style="height:30px; width: 380px" /><br />
     <input type="submit" value="찾기"  class="find" /><br />
     </form>
     <hr />
     <p><a href="pwfind.do"><input type="button" value="비밀번호찾기"  id="signup"></a></p>
</div>
</body>
</html>