<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
</head>
<body>
<%
	//"num"이라는 파라미터 명으로 전달된 문자열 읽어오기
	String strNum=request.getParameter("num");
	//문자열을 만일 정수로 바꾸고 싶으면?
	int num=Integer.parseInt(strNum);
%>
<p><%=num %>번 자세히 보기 ok!</p>
</body>
</html>