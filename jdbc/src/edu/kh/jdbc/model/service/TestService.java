package edu.kh.jdbc.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {
	// Service : 비즈니스 로직 (데이터가공, 트랜잭션 제어) 처리
	
	private TestDAO dao = new TestDAO();
	
	
	/** 1행 삽입 서비스
	 * @param vo1
	 * @return
	 */
	public int insert(TestVO vo1) throws Exception {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// 결과 반환 받기
		int result =  dao.insert(conn, vo1); // 1 or 0 
		
		// 트랜잭션 제어
		if(result > 0) commit(conn);
		else				rollback(conn);
		 
		// 커넥션 반환 
		close(conn);
		
		// 결과 반환
		return result;
	}
	
public int update(TestVO vo) throws Exception {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// 결과 반환 받기
		int result =  dao.update(conn, vo); // 1 or 0 
		
		// 트랜잭션 제어
		if(result > 0) commit(conn);
		else				rollback(conn);
				 
		// 커넥션 반환 
		close(conn);
		
		// 결과 반환
		return result;
	}
	
}
