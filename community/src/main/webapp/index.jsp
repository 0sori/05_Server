<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>17_웹 문서 구조</title>

    <link rel="stylesheet" href="resources/css/main-style.css">

    <!--  fontawesome 사이트 아이콘 이용   -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
     
        <!-- jsp:include 태그
        	다른 jsp파일의 내용을 해당 위치에 포함시킴
        	* 경로 작성시
        	 내부 접근 경로 (최상위 : /webapp )
         -->
         
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <section class="content">
            <section class="content-1">
            	<h3>회원 정보 조회(AJAX)</h3>
            	<p>이메일을 입력받아 일치하는 회원 정보를 출력</p>
            
            	이메일 : <input type="text" id="in1">
            	<button id="select1">조회</button>
            	<div id="result1" style="height:150px;"></div>
            
             <hr>

                <h3>회원 목록 조회</h3>

                <p>일정 시간 마다 비동기로 회원 목록(회원 번호, 이메일, 닉네임) 조회</p>

                <table border="1">
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>이메일</th>
                            <th>닉네임</th>
                        </tr>
                    </thead>


                    <tbody id="memberList">
                    
                    
                    </tbody>
	            </table>
            </section>

            <section class="content-2">
            
            	<%-- if/else --%>
            	<c:choose>
            		<%-- choose 내부에는 jsp용 주석만 사용 --%>
            		
            		<%-- 로그인이 되어있지 않은 경우 --%>
            		<c:when test="${ empty sessionScope.loginMember }">
		                <form action="member/login" method="POST" name="login-form" onsubmit="return loginValidate()">
		                    <fieldset id="id-pw-area">
		                        <section>
		                            <input type="text" name="inputEmail"
		                             placeholder="이메일" autocomplete="off"
		                             value="${cookie.saveId.value}"
		                             >
		        
		                            <input type="password" name="inputPw" placeholder="비밀번호">
		                        </section>
		        
		                        <section>
		                            <button>로그인</button>
		                        </section>
		                    </fieldset>
		        
		                    <label>
		                        <input type="checkbox" name="saveId"> 아이디 저장
		                    </label>
		        
		        			<!-- 
		        				WEB-INF 폴더는 외부로 부터 직접적으로 요청할 수 없는 폴더
		        				왜? 중요한 코드(설정관련, 자바..) 가 위치하는 폴더로서
		        				외부로부터 접근을 차단하기 위해서
		        				
		        				-> 대신 Servlet을 이용해서 내부 접근(forward) 가능
		        			 -->
		                    <article id="signUp-find-area">
		                        <a href="${contextPath}/member/signUp">회원가입</a>
		                        <span>|</span>
		                        <a href="#">ID/PW찾기</a>
		                    </article>
		                </form>
	                </c:when>
	                
	                
            		<%-- 로그인이 된 경우 --%>
            		<c:otherwise>
            			<article class="login-area">
            				<a href="${pageContext.request.contextPath}/member/myPage/profile">
                                
                                <c:if test="${empty loginMember.profileImage}">
                                    <img src="${pageContext.request.contextPath}/resources/images/user.png" id="member-profile">
                                </c:if>

                                <c:if test="${!empty loginMember.profileImage}">
                                    <img src="${pageContext.request.contextPath}/${loginMember.profileImage}" id="member-profile">
                                </c:if>

            				</a>
                                    
                            <div class="my-info">
                                <div>
                                    <a href="${pageContext.request.contextPath}/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>

                                    <a href="/community/member/logout" id="logout-btn">로그아웃</a>
                                </div>

                                <p>
                                    ${loginMember.memberEmail}
                                </p>
                            </div>
            			</article>
            		</c:otherwise>
            	
            	</c:choose>
            
            </section>
        </section>

    </main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	
	
	<!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    <!-- main.js 연결 -->
    <script src="${contextPath}/resources/js/main.js"></script>

    
</body>
</html>