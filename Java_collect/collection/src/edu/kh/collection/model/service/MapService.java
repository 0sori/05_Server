package edu.kh.collection.model.service;

import java.util.HashMap;
import java.util.Map;

import edu.kh.collection.model.vo.Student;

public class MapService {
	
	// Map : key와 value 한 쌍이 데이터가 되어 이를 모아둔 객체
	
	// - key : Map에 저장된 데이터를 구분 용도
	//		-> Set의 특징을 지님(순서X, 중복X, 
	//			equals(),hashCode() 오버라이딩o)\
	
	// - value : Map에 실제 저장된 값을 의미
	//		-> List의 특징을 지님(중복 O)
	
	public void ex1() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		// Map.put(key, value) : Map에 추가
		map.put(1, "홍길동");
		map.put(2, "고길동");
		map.put(3, "김길동");
		map.put(4, "박길동");
		map.put(5, "조길동");
		map.put(6, "홍길동");
		
		map.put(1, "홍홍홍"); // key가 중복되면 마지막껄로 덮어쓰기
		
		System.out.println(map.toString());
		
	}
	
	public void ex2() {
		
		// Map은 언제 사용하면 좋을까?
		
		// 1) 재사용이 적은 VO를 대체하는 경우
		// 2) 한번에 다량의 데이터를 전달하는 경우
		//     데이터의 명확한 구분을 위해서 사용
		
		
		Student std = new Student();
		Map<String, Object> student = new HashMap<String, Object>();
		
		// 값 추가
		std.setName("홍길동");
		student.put("name", "홍길동"); // map
		
		std.setAge(15);
		student.put("age", 15); // map
		
		std.setAddress("서울시 중구");
		student.put("address", "서울시 중구"); // map
		
		std.setGender('남');
		student.put("gender", '남'); // map
		
		std.setScore(100);
		student.put("score", 100); // map
		
		System.out.println(std);
		System.out.println(student);
		
		System.out.println("-----------------------------");
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getAddress());
		System.out.println(std.getGender());
		System.out.println(std.getScore());
		
		System.out.println("-----------------------------");
		
		//System.out.println(student.get("age"));
		
		// keySet() : Map에서 key부분만을 추출하여 Set형태로 반환
		for(String key : student.keySet()) {
			System.out.println(student.get(key));
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
