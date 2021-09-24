<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%	// 줄바꿈 
    pageContext.setAttribute("br", "<br/>");
    pageContext.setAttribute("cn", "\n");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.bbstitle } - 게시판</title>
<script>
function onDownload(bbsId){
	var o = document.getElementById("ifrm_filedown");
	o.src = "download.do?bbsId="+bbsId;
}
</script>
<style>
* {
	margin: 4px 0;
}

.controller {
	padding: 25px 0;
	margin: auto;
	width: 950px;
}
.controller2 {
	margin: auto;
	width: 950px;
}
.controller3 {
	margin: auto;
	width: 950px;
}
#bbsTitle {
	text-align: center;
	background-color: rgb(100, 100, 100);
	width: 950px;
	height: 40px;
	padding: 12px 0;
	color: white;
}

table {
	width: 950px;
	margin: 25px 0;
	padding: 20px;
	border-collapse: collapse;
}

tr {
	height: 40px;
}

#content {
	width: 800px;
	height: 400px;
	text-align: left;
}

.btn1 {
	width: 100px;
	padding: 5px 12px;
	border: none;
	background-color:  rgb(100, 100, 100);
	color: white;
	align: center;
}

.btn2 {
	padding: 5px 12px;
	background-color: white;
	border-color: rgb(180, 180, 180);
	border-width: 1px;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<iframe id="ifrm_filedown" style ="position:absolute;z-index:1;visibility:hidden;"></iframe>
<%@ include file="../menu/top.jsp" %>
	<div class="controller">
		<!-- <div id="bbsTitle"><b>게시글 보기</b></div>  -->
		<h3 align="center"><b>[ 게시글 보기 ]</b></h3>
		<table>
			<tr>
				<td colspan="3" align="left"><h4><b>제목</b> :&nbsp;&nbsp;${detail.bbstitle }</h4></td>
			</tr>
			<tr>
				<td width="30%"><b>카테고리</b> :&nbsp; ${detail.bbscategory }</td>
				<td width="30%"><b>작성자</b> :&nbsp; ${detail.id }</td>
				<td width="30%" align="right">${detail.bbsdate }</td>
			</tr>
			<tr id="content" valign="top" style="border-top-color: rgb(100, 100, 100); border-top-width: 1px">
				<td colspan="3"><br>${fn:replace(detail.bbscontent, cn, br)}</td>
			</tr>
			<tr>
				<td colspan="3"><b>조회수</b> : &nbsp;${detail.bbshit }</td>
			</tr>
			<tr>
			<td colspan="3"><b>파일</b> :&nbsp; <a href="#" onclick="onDownload('${detail.bbsid}')">${detail.b_fname}</a></td>
			</tr>
		
		<c:choose>
			<c:when test="${detail.id == sessionID }">
				<td colspan="3" align="right">
					<a onclick="return confirm('정말 삭제하시겠습니까?')"
					href="bbsdelete.do?bbsId=${detail.bbsid}">
						<button class="btn1">삭제</button></a>
				
					<a href="bbsupdate.do?bbsId=${detail.bbsid}">
						<button class="btn1">수정</button></a>
				</td>
			</c:when>
		</c:choose>
		</table>
		<!-- 댓글 리스트 영역 --> 
		<div class="container">
			<div class="row">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
				<c:forEach var="comm" items="${list }" >
			 	<div class="container">
				<div class="row">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tbody>
					<tr>						
					 <td colspan="2" align="left">${comm.id }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${comm.commdate }</td>		
				    </tr>
				    <tr>
					  <td align="left">${comm.commtext }</td>
					</tr>
					<c:choose>
					 <c:when test="${comm.id == sessionID }">
					 <tr>
					 <td colspan="2" align="right"><a onclick="return confirm('댓글 삭제하시겠습니까?')" href="comdelete.do?commid=${comm.commid}&bbsId=${detail.bbsid}">
						<button class="btn1">삭제</button></a></td></tr>
					 </c:when>
					</c:choose>				
					</tbody>
					</table>			
				 </div>
				</div>
				 </tr>
				 </c:forEach>
				</table>
			</div>
		</div>
		<!-- 댓글 리스트 영역 끝--> 
		<!-- 댓글 달기 영역--> 
		<div class="controller2">
		<c:if test="${sessionID != null}">
			<div class="form-group">
				<form method="post" action="">
				<input type="hidden" name="bbsID" value="${detail.bbsid }">
				<input type="hidden" name="id" value="${sessionID }">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br><br><b>${sessionID }</b></td>
					<td><input type="text" style="height:100px; width:700px;" class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다." name = "commentText"></td>
					<td><br><br><input type="submit" class="btn1" value="댓글 작성"></td>
				</tr>
				</table>
				</form>
			</div>
		</c:if>
		</div>
		 <!-- 댓글 달기 영역 끝 -->
		<p class ="controller3" align="right"><a href="list.do"><button class="btn2">목록</button></a></p>
	</div>
</body>
</html>