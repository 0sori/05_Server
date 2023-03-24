package edu.kh.community.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.community.common.JDBCTemplate.*;

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
	
	// 회원 정보 조회
	public Member selectOne(Connection conn, String memberEmail) throws Exception {

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

		} finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}

	// 회원 목록 조회
	public List<Member> selectAll(Connection conn) throws Exception {

		List<Member> memberList = new ArrayList<>();
		Member member = null;

		try {
			String sql = prop.getProperty("selectAll");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
                member = new Member();
                
                member.setMemberNo(rs.getInt(1));
                member.setMemberEmail(rs.getString(2));
                member.setMemberNickname(rs.getString(3));

                memberList.add(member);
                
             }

		} finally {
			close(rs);
			close(pstmt);
		}

		return memberList;
	}


}
