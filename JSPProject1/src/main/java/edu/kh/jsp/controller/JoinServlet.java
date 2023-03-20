package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	
	String id = req.getParameter("inputId");
	String pw = req.getParameter("inputPw");
	String pw2 = req.getParameter("inputPw2");
	
	String result = null;
	if(pw.equals("pass01!") &&pw2.equals("pass01!") ) {
		result = id + "님의 회원가입이 완료되었습니다";
	} else {
		result = id + "님의 회원가입이 실패되었습니다";
	}
	
	// JSP 경로 작성
	RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/joinResult.jsp");
	req.setAttribute("r", result);
	
	dispatcher.forward(req, resp);
	
	
	
	}
}
