<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 결과 페이지</title>
</head>
<body>
    <h1>회원가입 결과</h1>
    <ul>
    	<li>아이디 	: <%= request.getParameter("inputId") %></li>
    	<li>비밀번호: <%= request.getParameter("inputPw") %></li>
    	<li>이름 	: <%= request.getParameter("inputName") %></li>
    	<li>이메일	: <%= request.getParameter("inputEmail") %></li>
    	<li>취미	: <%= request.getParameterValues("check") %></li>
    </ul>
    
    <h3><%= request.getParameter("inputName") %> 님의 회원가입이 완료 되었습니다.</h3>
    
    
    
</body>
</html>