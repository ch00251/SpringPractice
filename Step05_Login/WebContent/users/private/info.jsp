<%@page import="test.users.dao.UsersDao" %>
<%@page import="test.users.dto.UsersDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 세션에서 로그인된 아이디 정보를 읽어온다
	String id=(String)session.getAttribute("id");
	//2. DB에서 해당 아이디의 정보를 얻어온다
	UsersDto dto=UsersDao.getInstance().getData(id);
	//3. 개인 정보를 응답해준다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/info.jsp</title>
</head>
<body>
<div class="container">
	<h1>개인정보 페이지</h1>
	<table>
		<tr>
			<th>아이디</th>
			<th><%=dto.getId() %></th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><a href="pwd_updateform.jsp">수정하기</a></th>
		</tr>
		<tr>
			<th>이메일</th>
			<th><%=dto.getEmail() %></th>
		</tr>
		<tr>
			<th>가입일</th>
			<th><%=dto.getRegdate() %></th>
		</tr>
	</table>
	<a href="updateform.jsp">개인정보 수정하기</a>
	<a href="javascript:deleteConfirm()">회원 탈퇴</a>
</div>
<script>
	function deleteConfirm(){
		var isDelete=confirm("<%=id %>님 탈퇴 하시겠습니까?");
		if(isDelete){
			location.href="delete.jsp";
		}
	}
</script>
</body>
</html>