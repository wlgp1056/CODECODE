<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom:0">
  <h1>Code Code</h1> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="<%=request.getContextPath() %>/CODECODE/">HOME</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
    <c:if test="${sessionID == null }">
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/CODECODE/user/login.do">로그인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/CODECODE/user/joinus.do">회원가입</a>
      </li>
     </c:if>
      <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath() %>/CODECODE/board/list.do">게시판</a>
      </li>    
    </ul>
  </div>  
</nav>
</body>
</html>