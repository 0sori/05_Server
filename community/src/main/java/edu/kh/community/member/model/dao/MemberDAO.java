package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;
import static edu.kh.community.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.member.model.vo.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberEmail) throws SQLException {

		Member member = null;

		try {
			String sql = prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();

				member.setMemberEmail(rs.getString(1));
				member.setMemberNickname(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberAddress(rs.getString(4));
				member.setEnrollDate(rs.getString(5));
			}

			System.out.println("member::" + member);

		} finally {
			close(rs);
			close(pstmt);

		}

		return member;
	}

	/**
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn) throws Exception {

		// 결과 저장용 변수 선언
		List<Member> mbList = new ArrayList<>();

		try {
			Member member = null;
			// SQL 작성하기
			String sql = prop.getProperty("selectAll");

			// Statement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// SQL 수행 후 결과 (ResultSet) 반환 받기
			rs = pstmt.executeQuery();

			while(rs.next()) {
				member = new Member();
				member.setMemberNo(rs.getInt(1));
				member.setMemberEmail(rs.getString(2));
				member.setMemberNickname(rs.getString(3));

				mbList.add(member);
			}
			System.out.println(mbList);

		} finally {
			// 사용한 JDBC 객체 자원반환
			close(rs);
			close(pstmt);
		}
		// 결과반환
		return mbList;
	}

	/** 로그인 DAO
	 * @param conn
	 * @param mem
	 * @return
	 */
	public Member login(Connection conn, Member mem) throws Exception {
		
		// 결과 저장용 변수 선언 (제일 먼저 해야 함)
		Member loginMember = null;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("login");
			
			// PreparedStatment 생성 및 SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			
			// SQL 수행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				
				loginMember.setMemberNo(  	   rs.getInt("MEMBER_NO") );
				loginMember.setMemberEmail(    rs.getString("MEMBER_EMAIL") );
				loginMember.setMemberNickname( rs.getString("MEMBER_NICK")	 );
				loginMember.setMemberTel(      rs.getString("MEMBER_TEL") 	 );
				loginMember.setMemberAddress(  rs.getString("MEMBER_ADDR")  );
				loginMember.setProfileImage(   rs.getString("PROFILE_IMG")  );
				loginMember.setEnrollDate(     rs.getString("ENROLL_DT") 	 );
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return loginMember; // null 또는 Member 객체 주소
	
	
	}
}