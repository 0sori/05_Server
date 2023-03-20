package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login") // web.xml에 작성하던 <servlet>, <servlet-mapping> 태그 대체 어노테이션
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// post 방식 요청 데이터 문자 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		// 전달 받은 파라미터를 얻어와 변수에 저장
		String id = req.getParameter("inputId");
		String pw = req.getParameter("inputPw");
		
		System.out.println(id);
		System.out.println(pw);
		
		String result = null;
		if( id.equals("user01") && pw.equals("pass01!") ) {
			result = "로그인 성공";
		} else {
			result = "아이디 또는 비밀번호 불일치";
		}
		
		// JSP 로 응답하기
		
		// Dispatcher : 발송자, 필요한 정보를 제공하는 자
		
		// RequestDispatcher == 요청을 위임하는 역할의 객체
		// -> (정확히는 요청에 대한 응답화면을 만들어)
		//		클라이언트에게 출력하는 역할)을 위임하는 객체
		
		// req.getRequestDispatcher("JSP 경로")
		// - HttpServletRequest 객체가 생성될때
		//   내부에 요청을 위임하는 객체를 생성하는 방법을 포함하고 있다
		
		// ** JSP 경로 작성 방법 **
		// - webapp 폴더를 기준으로 파일 경로 작성
		
		// forward : 보내다, 전달하다, 전송하다
		// - forward는 페이지 이동이 아닌
		// JSP에게 req, resp를 전달만 하는 것.
		// -> JSP 응답을 대신하는 것 뿐
		// 결론 : 페이지 이동 X -> 주소창의 요청 주소도 변하지 않는다!
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		
		// req.setAttribute(String key, Object value)
						// 모든 타입 가능
		req.setAttribute("r", result);
						// 업캐스팅 되어있는 상태 
		
		
		dispatcher.forward(req, resp);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
