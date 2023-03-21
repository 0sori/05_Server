<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
	prefix : 접두사
	uri(Uniform Resource Indentifier) : 자원 구분 식별자(주소형태)
 -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3. JSTL</title>
    
    <style>
    	.frist{ background-color: yellow; }
    	
    	.last{
    		background-color: black;
    		color: white;
    	}
    </style>
</head>
<body>
    <h1>JSTL(Jsp Standard Tag Library)</h1>
    <pre>
		JSP에서 자주 사용되거나 고통적으로 사용되느 Java 코드를
		표기법을 간단히 태그화하여 표준으로 제공하는 것.
		
		(if, for, 배열/컬렉션 길이, 문자열 치환, 숫자데이터 형식 변경
			데이터 파싱, scope 변수 선언등 )
    </pre>
    
    <h3>JSTL 라이브러리 등록 방법</h3>
	
	<ol>
		<li>https://tomcat.apache.org/download-taglibs.cgi 접속</li>
		<li>Jar Files -> Impl. Spec, EL 다운로드</li>
		<li>webapp/WEB-INF/lib 폴더에 추가하기</li>
	</ol>
	
	<hr>
	
	<h3>JSTL 사용을 위한 선언 방법</h3>
	<pre>
		JSP 파일 최상단에 추가하고자하는 JSTL taglib를 추가
	</pre>
	
	<hr>
	
	<h1>1. 변수 선언( c:set 태그)</h1>
	<pre>
		- 변수를 선언하고 값을 초기화 하는 ㅐ그
			(원하는 scope의 내장 객체에 값 세팅)
			
		- c:set 속성
		1) var : 변수명(setAttribute의 key값)
		2) value : 대입될 값
		3) scope : 내장 객체 범위 (기본값 : page)
	</pre>
	
	<!-- script 작성법 -->
	<% pageContext.setAttribute("num1", 10); %>
	
	<!-- JSTL 작성법 -->
	<c:set var="num2" value="20" />
	
	${ num1 } / ${ num2 }
	
	<!-- request scope에 num2 세팅 -->
	<c:set var="num2" value="300" scope="request" />
	
	<h4>${num2}</h4>
	<h4>${requestScope.num2}</h4>
	
	<c:set var="temp" value="임시값" scope="session"/>
	
	<h4>temp : ${temp}</h4>
	
	<hr>
	
	<h1>2. 변수 삭제 (c:remove)</h1>
	<pre>
		- c:set / setAttribute() 로 추가된 값 제거
		
		- c:remove 속성
		1) var	 : 삭제할 변수명(key)
		2) scope : 삭제할 내장 객체 범위 (기본값:모든 범위)
	</pre>
	
	<p>
		<c:remove var="temp"/>
		temp : ${temp} <br>
		
		<c:set var="test" value="page" scope="page"/>
		<c:set var="test" value="request" scope="request"/>
		<c:set var="test" value="session" scope="session"/>
		<c:set var="test" value="application" scope="application"/>
		
		<c:remove var="test" scope="request"/>
		
		
		
		${pageScope.test} / ${requestScope.test}
		/ ${sessionScope.test} / ${applicationScope.test} 
	</p>
	
	<hr>
	
	<h1>3. 조건문 - if ( c:if 태그 )</h1>
	<pre>
		- 단독 if문 (else 없음)
		
		* 주의사항 *
		1) test 속성 값 작성은 무조건 EL 구문이어야한다.
		2) test 속성 값 "" 안에는 공백이 존재해서는 안된다.
	</pre>
	
	<!-- Scriptlet 작성법 -->
	
	<% if(1== 1) { %>
		출력합니다
	<% } %>
	
	<%-- JSTL 작성법 --%>
	<c:if test="${1 ==1}">
		JSTL 작성법
	</c:if>
	
	<hr>
	
	<h1>4. 조건문 - if ~ else if ~ else ( c:choose, c:when, c:otherwise)</h1>
	<pre>
		c:choose : when, otherwise를 감싸는 태그
				(현재 태그 내부에 if ~ else if ~ else 를 작성하겠다.)
		
		c:when : if / else if 를 나타내는 태그
				속성은 test밖에 없음 (조건 작성 속성)
				
		c:otherwise : else를 나타내는 태그
					아무런 속성도 존재하지 않음
					
	</pre>
		
	<c:set var="temp2" value="150"/>
	
	<c:choose>
		<c:when test="${ temp2 > 100 }">
			100보다 크다
		</c:when>
		
		<c:when test="${ temp2 < 100 }">
			100보다 작다
		</c:when>
		
		<c:otherwise>
			100과 같다
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	<h1>5. 반복문 ( c:forEach 태그 )</h1>
	<pre>
		- 일반 for + 추가 기능
		
		- 속성
		1) var : 현재 반복 횟수에 해당하는 변수 (int i)
		2) begin : 반복 시 var 시작 값
		3) end : 반복이 종료될 var 값
		4) step : 반복 시 마다 var의 증가 값 (기본값 1)
		
		5) items : 반복 접근한 객체(배열, 컬렉션 객체)
		
		6) varStatus : 현재 반복 상태와 관련된 정보를 제공하는 변수 선언
		
			varStatus="변수명"
			-> c:forEach 구문 내에서 "변수명"을 통해 원하는 값을 얻을 수 있다.
		
			* varStatus에서 제공되는 값
			- current : 현재 반복 횟수(현재 var 값)
			 		또는 현재 반복 접근 중인 객체(컬렉션/배열 요소)
			 		
			- index : 현재 인덱스값 반환 (0부터 시작)
			
			- count : 현재 몇바퀴째인지 반복 횟수 반환 (1부터 시작)
			
			- first : 첫 번째 반복이면 true, 아니면 false
			
			- last : 마지막 반복이면 true, 아니면 false

		
	</pre>
	
	<h3>일반 for문 형식으로 사용</h3>
	
	<c:forEach var="i" begin="1" end="6" step="1">
		<h${i}> 현재 i값 : ${i} </h${i}>
	</c:forEach>
	
	<h3>일반 for문 + varStatus</h3>
	
	<table border="1">
		<c:forEach var="n" begin="1" end="10" varStatus="vs">
			
			<c:choose>
				<%-- 첫번째 반복일 경우 --%>
				<c:when test="${vs.first}">
					<tr>
						<th class="frist">${n}</th>
						<td class="frist">${n}번게시글 입니다.</td>
					</tr>
				</c:when>

				<%-- 마지막 반복일 경우 --%>
				<c:when test="${vs.last}">
					<tr>
						<th class="last">${n}</th>
						<td class="last">${n}번게시글 입니다.</td>
					</tr>
				</c:when>

				<c:otherwise>
					<tr>
						<th>${n}</th>
						<td>${n}번째 게시글 입니다</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</c:forEach>
	</table>
	
	<hr>
	
	<h3>향상된 for문 형식으로 사용</h3>
	
	<form action="forEach" method="get">
		<input type="checkbox" name="lang" value="java"> java <br>
		<input type="checkbox" name="lang" value="sql"> sql <br>
		<input type="checkbox" name="lang" value="jdbc"> jdbc <br>
		<input type="checkbox" name="lang" value="html"> html <br>
		<input type="checkbox" name="lang" value="css"> css <br>
		<input type="checkbox" name="lang" value="javascript"> javascript <br>
		<input type="checkbox" name="lang" value="jQuery"> jQuery <br>
		<input type="checkbox" name="lang" value="servlet"> servlet <br>
		<input type="checkbox" name="lang" value="jsp"> jsp <br>
		
		<button>제출</button>
	</form>

	
	
	
	
	
	
	
	
	
	
	
</body>
</html>