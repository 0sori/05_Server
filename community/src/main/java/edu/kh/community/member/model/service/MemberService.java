package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	
	/** 회원정보 조회 Service
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public Member selectOne(String memberEmail) throws Exception {

		Connection conn = getConnection();

		Member member = dao.selectOne(conn, memberEmail);

		close(conn);

		return member;
	}
	
	
	/** 회원 목록 조회 Service
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectAll() throws Exception {
		Connection conn = getConnection();
		
		List<Member> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	/**
	 * 로그인 서비스
	 * 
	 * @param mem
	 * @return loginMember
	 */
	public Member login(Member mem) throws Exception {
		// Connection 얻어오기
		Connection conn = getConnection();

		// DAO 수행
		Member loginMember = dao.login(conn, mem);

		// Connection 반환
		close(conn);

		// 결과 반환
		return loginMember;
	}

	/**
	 * 이메일 중복검사 Service
	 * 
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(String memberEmail) throws Exception {
		Connection conn = getConnection();

		int result = dao.emailDupCheck(conn, memberEmail);

		close(conn);

		return result;
	}

	/**
	 * 인증번호 DB 추가 Service
	 * 
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int insertCertification(String inputEmail, String cNumber) throws Exception {

		Connection conn = getConnection();

		// 1) 입력한 이메일과 일치하는 값이 있으면 수정(UPDATE)
		int result = dao.updateCertification(conn, inputEmail, cNumber);

		// 2) 일치하는 이메일이 없는 경우 -> 처음으로 인증번호 발급받음 -> 삽입(INSERT)
		if (result == 0) {
			result = dao.insertCertification(conn, inputEmail, cNumber);
		}

		// 트랜잭션 제어! (DML 구문)
		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		// 커넥션 반환
		close(conn);

		return result;
	}

	/**
	 * 인증번호 확인 service
	 * 
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int checkNumber(String inputEmail, String cNumber) throws Exception {
		Connection conn = getConnection();

		int result = dao.checkNumber(conn, inputEmail, cNumber);

		close(conn);

		return result;
	}

	/**
	 * 닉네임 중복 검사 service
	 * 
	 * @param memberNickname
	 * @return
	 */
	public int nicknameDupCheck(String memberNickname) throws Exception {
		Connection conn = getConnection();

		int result = dao.nicknameDupCheck(conn, memberNickname);

		close(conn);

		return result;
	}

	/**
	 * 프로필 이미지 변경 Service
	 * 
	 * @param memberNo
	 * @param profileImage
	 * @return
	 */
	public int updateProfileImage(int memberNo, String profileImage) throws Exception {
		Connection conn = getConnection();

		int result = dao.updateProfileImage(conn, memberNo, profileImage);

		// 트랜잭션 제어 처리
		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	/**
	 * 회원가입 Service
	 * 
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception {

		// 1) Connextion 얻어오기
		Connection conn = getConnection();

		// 2) DAO 메소드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, mem);

		// 3) 트랜잭션 처리
		// result가 0인 경우 -> DAO return 구문 잘못 작성

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		// 4) conn 반환(DBCP로 돌려주기)
		close(conn);

		// 5) 결과반환
		return result;
	}

	/** 전화번호 유효성 검사 Service
	 * @param memberTel
	 * @return
	 * @throws Exception
	 */
	public int telDupCheck(String memberTel) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.telDupCheck(conn, memberTel);
		
		close(conn);
		
		return result;
	}

	

	/** 비밀번호 변경 Service
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {
		
		Connection conn = getConnection(); // DBCP에서 얻어옴
		
		int result = dao.changePw(conn, currentPw, newPw, memberNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 내정보 수정 Service
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, mem);
		
		// 트랜잭션
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 종료
		close(conn);
		
		
		return result;
	}

	/** 회원 탈퇴 Service
	 * @param memberNo
	 * @param memberPw
	 * @return
	 * @throws Exception
	 */
	public int secession(int memberNo, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.secession(conn, memberNo, memberPw);
		
		if (result >0) commit(conn);
		else  		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
