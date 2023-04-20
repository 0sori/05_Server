package edu.kh.collection.model.service;

import java.util.ArrayList;

import edu.kh.collection.model.vo.Student;

public class ListService {
	
	public void ex1() {
		
		// java.util 패키지
		
		// ArrayList list = new ArrayList(); // 기본 생성자 -> 10칸짜리 생성
		
		ArrayList list = new ArrayList(3);
		
		// add(E e) : 리스트에 마지막 위치에 객체 추가
		list.add( new Object() );
		list.add( new String() );
		list.add( new Student() );
		
		// ArrayList 의 크기 3을 초과해서 추가
		list.add( new String("초과") );
		
		//System.out.println(list);
		
		// add(int index, E e) : index 위치에 E 객체를 추가
		list.add(1, new Student("홍길동", 15, "강남구", '남', 60) );
		System.out.println(list);
		
		// set(int index, E e) : index 위치를 E 객체로 수정
		//						원래 위치하던 객체를 반환
		Object str = list.set(4, new String("수정된 문자열"));
		
		System.out.println(str);
		System.out.println(list);
		
		// remove(int index) : index 위치의 객체를 꺼내서 반환
		Object student = list.remove(2);
		
		System.out.println(student);
		System.out.println(list);
		
		// size() : List에 저장된 요소의 개수를 반환
		// get(int index) : index에 위치한 객체를 얻어옴
		for(int i=0; i<list.size(); i++) {
			
			if( list.get(i) instanceof Student ) {
				System.out.println( ((Student)list.get(i)).getName() );
			}
			
		}
		
		
	}
}
