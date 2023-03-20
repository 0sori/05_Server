<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 결과</title>
    
    <style>
    	#container {
    		width: 400px;
    		border	: 1px solid black;
    		margin	: auto;
    	}
    </style>   
</head>
<body>
    <%
    	int total = (int)request.getAttribute("tot");
    	
    	String pizza = request.getParameter("pizza");
    	String size = request.getParameter("size");
    	String amount = request.getParameter("amount");
    %>
    
    <div id="container">
    	주문한 피자 : <%= pizza %> <br>
    	
    	<% if(size.equals("L")) { %>
    			
    		
    	<% } else { %>
    		Regular
    	<% } %>
    	
    	<br>
    	
    	수량 : <%= amount %> 판
    	
    	<h4> 총 합계 : <%= total %></h4>
    	
    </div>
</body>
</html>