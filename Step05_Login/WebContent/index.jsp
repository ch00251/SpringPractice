<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	//session에서 "id"라는 키값으로 저장된 문자열이 있는지 읽어와 본다
 	//있으면 로그인 된 상태, null이면 로그인 안된 상태
 	String id=(String)session.getAttribute("id");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
<div class="container">
	<h1>인덱스 페이지 입니다.</h1>
	<%if(id==null){ %>
		<ul>
			<li><a href="users/signupform.jsp">회원가입</a></li>
			<li><a href="users/loginform.jsp">로그인</a></li>
		</ul>
	<%}else{ %>
		<p>
			<strong>
				<a href="${pageContext.request.contextPath }/users/private/info.jsp"><%=id %></a>
			</strong> 님 로그인중...
			<a href="users/logout.jsp">로그아웃</a>
		</p>
	<%} %>
	<ul>
		<li><a href="shop/buy.jsp?num=1?&name=coffee">1번 상품 구입하기</a></li>
		<li><a href="shop/buy.jsp?num=2&name=sugar">2번 상품 구입하기</a></li>
		<li><a href="cafe/list.jsp">글 목록보기</a></li>
	</ul>
</div>
</body>
</html>