package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/selectAll")
public class SelectAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			MemberService service = new MemberService();
			List<Member> mbList = service.selectAll();

			// 3) GSON 라이브러리를 이용한 JAVA 객체 > Json 변환
			// new Gson().toJson ( 객체, 응답스트림 );
			// >> 매개변수에 작성 된 객체를 JSON형태로 변환한 후 스트림 통해서 출력

			new Gson().toJson(mbList, resp.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
