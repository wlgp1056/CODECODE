<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
* {
	margin: 4px 0;
}

.controller {
	padding: 25px 0;
	margin: auto;
	width: 950px;
}

#wriTitle{
	text-align: center;
	background-color: rgb(100, 100, 100);
	width: 950px;
	height: 40px;
	color: white;
	padding: 12px 0;
}
table {
	width: 950px;
	margin: 25px 0;
	padding: 20px;
	padding: 10px 0;
	border-collapse: collapse;
}

#category {
	width: 150px;
	height: 30px;
}

#title {
	width: 850px;
	height: 30px;
}

textarea {
	width: 950px;
	height: 350px;
}

.button {
	width: 100px;
	padding: 5px 12px;
	border: none;
	background-color: rgb(100, 100, 100);
	color: white;
}

button {
	padding: 5px 12px;
	background-color: white;
	border-color: rgb(180, 180, 180);
	border-width: 1px;
}

textarea {
	resize: none;
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
	<!-- <div id="wriTitle"><b>게시글 작성</b></div>   -->
	<h3 align="center"><b>[ 게시글 작성 ]</b></h3> 
		<form action="" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td width="100px"><b>카테고리</b></td>
					<td><select name="bbsCategory" id="category" >
							<option>Java</option>
							<option>JSP</option>
							<option>C++</option>
							<option>Python</option>
							<option>기타</option>
					</select></td>
				</tr>
				<tr>
					<td><b>제목</b></td>
					<td><input type="text" name="bbsTitle" required id="title" />
					</td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td colspan="2"><textarea rows="10" cols="40" 
							name="bbsContent" required></textarea></td>
				</tr>
				<tr>
					<td><b>File</b></td>
					<td><input type="file" name="b_fname" id="title" /></td>
				</tr>
				<tr><td colspan="2" align="right">
				     <input type="submit" value="작성" class="button">
					 <input type="reset" value="초기화" class="button"></td>
				</tr>
			</table>
		</form>
		<div>
			<a href="list.do">
				<button>뒤로가기</button>
			</a>
		</div>
	</div>
</body>
</html>