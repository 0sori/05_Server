package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	public Member selectOne(String memberEmail) throws Exception {

		Connection conn = getConnection();

		Member member = dao.selectOne(conn, memberEmail);

		close(conn);

		return member;
	}

	public List<Member> selectAll() throws Exception {

		Connection conn = getConnection();

		List<Member> mbList = dao.selectAll(conn);

		return mbList;
	}

	public Member login(Member mem) throws Exception {
		// Connection 얻어오기
		Connection conn = getConnection();
		// Dao 수행
		Member loginMember = dao.login(conn, mem);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginMember;
	}

}
