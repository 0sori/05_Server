package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/selectOne")
public class SelectOneServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 피라미터 얻어오기
		String memberEmail = req.getParameter("memberEmail");

		try {
			MemberService service = new MemberService();

			Member member = service.selectOne(memberEmail);

			// ** Java 객체를 Javascript객체로 변환하여 응답(출력) **
			// Java 객체 -> Javascript형태 문자열인 JSON -> Javascript 객체

			// 1) JSON 직접 작성 -> 오타
			// String str = "{
			System.out.println(member);
			new Gson().toJson(member, resp.getWriter());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
