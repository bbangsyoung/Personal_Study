<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.ddd
{
  	margin: 0 auto;
	width:700px;
	border: 1px solid red;
}
h1,h3,input{text-align:center; margin:auto;}
</style>
<title>글 목록</title>
</head>
<body>
<div class="ddd">
		<h1>글 목록</h1>
		<h3>
			${userName}님! 환영합니다...<a href="logout.do">Log-out</a>
		</h3>
		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
					<select name="searchCondition">
								<c:forEach items="${conditionMap}" var="option">
									<option value="${option.value}"> ${option.key}
								</c:forEach>
					</select> 
					<input name="searchKeyword" type="text" /> <input type="submit"
						value="검색" /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
			<c:forEach items="${boardList}" var="board">
				<tr>
					<td>${board.seq }</td>
					<td align="left"><a href="getBoard.do?seq=${board.seq }">
							${board.title }</a></td>
					<td>${board.writer }</td>
					<td>${board.regDate }</td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</table>
		<br> <a href="insertBoard.jsp" class="btn">새글 등록</a>
		</div>
</body>
</html>