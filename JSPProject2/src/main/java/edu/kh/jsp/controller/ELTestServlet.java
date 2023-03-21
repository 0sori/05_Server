package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.vo.Person;

@WebServlet("/elTest")
public class ELTestServlet extends HttpServlet {
	// ** 데이터 전달 방식에 따라서
	//	  하나의 요청 주소로 여러가지 처리가 가능하다 !!! **
	
	
	// a태그로 요청(GET)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher
			= req.getRequestDispatcher("/WEB-INF/views/el/elTest.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	// form태그 요청(POST)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 데이터 문자 인코딩 지정
		req.setCharacterEncoding("UTF-8");
		
		// 파라미터 얻어오기
		String name = req.getParameter("inputName");
		int age = Integer.parseInt(req.getParameter("inputAge"));
		String address = req.getParameter("inputAddress");
		
		String message = name + "님은" + age + "세 이고," + address + "에 거주중 입니다.";
		
		
		// Person 객체 생성
		Person p = new Person();
		
		p.setName(name + "님");
		p.setAge(age + 10000);
		p.setAddress("대한민국" + address);
		
		
		
		
		
		RequestDispatcher dispatcher
		= req.getRequestDispatcher("/WEB-INF/views/el/elResult.jsp");
		
		// 요청 위임시 추가할 값 세팅
		req.setAttribute("message", message);
		req.setAttribute("person", p);
		
		
		List<String> list1 = null;
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		list3.add("테스트");
		
		req.setAttribute("List1", list1);
		req.setAttribute("List2", list2);
		req.setAttribute("List3", list3);
		
		// JSP로 요청 위임
		dispatcher.forward(req, resp);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
