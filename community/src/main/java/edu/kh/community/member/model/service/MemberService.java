package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
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
		Connection conn =getConnection();
			
			List<Member> memberList =dao.selectAll(conn);
			close(conn);
			
			return  memberList;
		}

}
