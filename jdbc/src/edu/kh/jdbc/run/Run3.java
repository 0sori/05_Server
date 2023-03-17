package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run3 {
	public static void main(String[] args) {
		// update
		
		// 번호, 제목, 내용 입력받아
		// 번호가 일치하는 행의 제목, 내용 수정
		
		// 수정 성공시 -> 수정되었습니다.
		// 수정 실패시 -> 일치하는 번호가 없습니다.
		// 예외 발생시 -> 수정중 예외가 발생했습니다.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("수정할 번호 입력 : ");
		int testNo= sc.nextInt();
		sc.nextLine();
		
		System.out.print("제목 : ");
		String testTitle = sc.nextLine();
		
		System.out.print("내용 : ");
		String testContent = sc.nextLine();
		
		
		TestService Service = new TestService();
		TestVO vo = new TestVO(testNo, testTitle, testContent);
		
		try {
			
			int result = Service.update(vo);
			
			if(result>0) {
				System.out.println("수정되었습니다");
			} else {
				System.out.println("일치하는번호가 없습니다.");
			}
			
		} catch(Exception e) {
			System.out.println("수정중 예외가 발생했습니다");
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
