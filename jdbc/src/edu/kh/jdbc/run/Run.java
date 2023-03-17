package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run {
	
	public static void main(String[] args) {
		
		
		TestService service = new TestService();
		
		
		
		TestVO vo1 = new TestVO(1, "제목1", "내용1");
		
//		try {
//			int result = service.insert(vo1); // 1 or 0
//			
//			if(result > 0) {
//				System.out.println("insert 성공");
//			} else {
//				System.out.println("insert 실패");
//			}
//			
//		} catch(Exception e) {
//			System.out.println("[SQL 수행중 오류발생]");
//			e.printStackTrace();
//		}
		
		
		
	}
}
