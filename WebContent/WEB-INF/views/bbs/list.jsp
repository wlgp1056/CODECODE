<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록</title>
<script>
function searchCheck(frm){
    //검색
    if(frm.keyWord.value ==""){
        alert("검색 단어를 입력하세요.");
        frm.keyWord.focus();
        return;
    }
    frm.submit();      
}
</script>
<style>
.controller {
	padding: 25px 0;
	margin: auto;
	width: 950px;
	text-align: center;
}
table {
	width: 950px;
	padding: 10px 0;
	border-collapse: collapse;
}
th {
	background-color: rgb(100, 100, 100);
	color: white;
}
button {
	margin: 4px 0;
	padding: 10px 0;
	width: 840px;
	background-color: rgb(255, 80, 80);
	color: white;
	border: none;
}
a {
	text-decoration: none;
	color: black;
}
a:hover {
	text-decoration-line: underline;
}
.btn1 {
	width: 100px;
	margin: 4px 0;
	padding: 5px 12px;
	border: none;
	background-color: rgb(100, 100, 100);
	color: white;
	align : right;
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
<div class="controller">
<h3 align="center"><b>오늘의 공부</b></h3>
<c:if test="${sessionID != null }">
	<p align="right">${sessionID } 로그인 중 <a href="<%=request.getContextPath()%>/CODECODE/user/logout.do"><input type="button" value="로그아웃"  id="signup" ></a></p>
<p align="right">
<a href="write.do"><button class="btn1">글쓰기</button></a><br />
</p>
</c:if>
<table>
<p align="left"><a href="list.do"><input type="button" value="전체리스트보기"  id="signup"></a></p>
<tr>
<th width="100px">카테고리</th>
<th width="40px">번호</th>
<th width="150px">제목</th>
<th>내용</th>
<th width="100px">작성자</th>
<th width="150px">날짜</th>
<th width="40px">조회</th>
</tr>
 <c:forEach var="bbs" items="${list }" >
	<tr>
	<td>${bbs.bbscategory }</td>
	<td>${bbs.bbsid}</td>
	<td><a href="detail.do?bbsId=${bbs.bbsid}">${bbs.bbstitle}</a></td>
	<td>${bbs.bbscontent}</td>
	<td>${bbs.id}</td>
	<td>${bbs.bbsdate}</td>
	<td>${bbs.bbshit}</td>
	</tr>
</c:forEach>
<!-- 검색 -->
 <tr align="center">  
        <td colspan="7"> <br/>
            <form action="">
            <select name="keyField">
                <option value="0"> ----선택----</option>
                <option value="id">작성자</option>
                <option value="bbstitle">제목</option>
                <option value="bbscategory">카테고리</option>
            </select>
            <input type="text" name="keyWord" />
            <input type="button" value="검색" onclick="searchCheck(form)" />
            </form>           
        </td>      
    </tr>
</table>
</div>
</body>
</html>
