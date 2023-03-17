package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run2 {

	public static void main(String[] args) {
		// TB_TEST 테이블에 한 번에 3행 삽입
		
		TestService Service = new TestService();
		
		TestVO vo2 = new TestVO(2, "제목2", "내용2");
		TestVO vo3 = new TestVO(3, "제목3", "내용3");
		TestVO vo4 = new TestVO(4, "제목4", "내용4");
		try {
			int result2 = Service.insert(vo2);
			int result3 = Service.insert(vo3);
			int result4 = Service.insert(vo4);
			
			
				if(result2==1 && result3==1 && result4==1) {
					System.out.println("insert 성공");
				} else {
					System.out.println("insert 실패");
				}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
