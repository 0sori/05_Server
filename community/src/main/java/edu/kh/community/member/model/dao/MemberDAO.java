package edu.kh.community.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
			
			prop.loadFromXML( new FileInputStream(filePath) );
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberEmail) throws Exception{
		
		Member member = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				
				member.setMemberEmail( rs.getString(1) );
				member.setMemberNickname( rs.getString(2) );
				member.setMemberTel( rs.getString(3) );
				member.setMemberAddress( rs.getString(4) );
				member.setEnrollDate( rs.getString(5) );
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}

	/** 로그인 DAO
	 * @param conn
	 * @param mem
	 * @return loginMember
	 */
	public Member login(Connection conn, Member mem) throws Exception {
		// 결과 저장용 변수 선언
		Member loginMember = null;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("login");
			
			// PreparedStatement 생성 및 SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			
			// SQL 수행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				loginMember = new Member();
				
				loginMember.setMemberNo(  rs.getInt("MEMBER_NO") );
				loginMember.setMemberEmail( 	rs.getString("MEMBER_EMAIL") );
				loginMember.setMemberNickname( 	rs.getString("MEMBER_NICK")	 );
				loginMember.setMemberTel( 		rs.getString("MEMBER_TEL") 	 );
				loginMember.setMemberAddress( 	rs.getString("MEMBER_ADDR")  );
				loginMember.setProfileImage( 	rs.getString("PROFILE_IMG")  );
				loginMember.setEnrollDate( 		rs.getString("ENROLL_DT") 	 );
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember; // null 또는 Member 객체 주소
	}

	/** 이메일 중복검사 DAO
	 * @param conn
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception{
		
		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("emailDupCheck");
			
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberEmail);
			
			// SQL 수행 후 결과 반환받기
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				result = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	/** 인증번호, 발급일 수정 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateCertification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 인증번호 생성 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertCertification");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 인증번호 확인하는 DAO 
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int checkNumber(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("checkNumber");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			pstmt.setString(3, inputEmail);
			
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	/** 닉네임 중복 검사 DAO
	 * @param conn
	 * @param memberNickname
	 * @return
	 */
	public int nicknameDupCheck(Connection conn, String memberNickname) throws Exception {

		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("nicknameDupCheck");
			
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더 알맞은 값 세팅
			pstmt.setString(1, memberNickname);
			
			// SQL 수행 후 결과 반환받기
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				result = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	/** 프로필 이미지 변경 DAO
	 * @param conn
	 * @param memberNo
	 * @param profileImage
	 * @return
	 */
	public int updateProfileImage(Connection conn, int memberNo, String profileImage) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateProfileImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, profileImage);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int signUp(Connection conn, Member mem) throws Exception {
		
		int result = 0; // 결과 저장용 변수
		 
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberNickname());
			pstmt.setString(4, mem.getMemberTel());
			pstmt.setString(5, mem.getMemberAddress());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		// 결과 반환
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
