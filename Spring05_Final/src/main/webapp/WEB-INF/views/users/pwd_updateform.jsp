<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/pwd_updateform.jsp</title>
<jsp:include page="../include/resource.jsp"/>
</head>
<body>
<div class="container">
	<h1>비밀번호 수정페이지</h1>
	<form action="pwd_update.do" method="post">
		<div>
			<label for="pwd">기존 비밀번호</label>
			<input type="password" id="pwd" name="id" />
		</div>
		<div>
			<label for="newPwd">새 비밀번호</label>
			<input type="password" name="newPwd" id="newPwd" />
		</div>
		<div>
			<label for="newPwd2">새 비밀번호 확인</label>
			<input type="password" name="newPwd2" id="newPwd2" />
		</div>
		<button type="submit">수정 확인</button>
	</form>
</div>
<script>
	//폼에 submit이벤트가 일어났을 때 실행할 함수 등록
	$("form").on("submit",function(){
		//새 비밀번호 2개가 일치하는지 확인
		var pwd=$("#newPwd").val();
		var pwd2=$("#newPwd2").val();
		if(pwd!=pwd2){
			alert("비밀번호를 확인하세요.");//알람 띄우기
			$("#newPwd").focus();//포커스주기
			return false;//폼 전송 막기
		}
	});
</script>
</body>
</html>