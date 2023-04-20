package edu.kh.collection.run;

import edu.kh.collection.model.service.ListService;
import edu.kh.collection.model.service.MapService;
import edu.kh.collection.model.service.SetService;

public class Run {

	public static void main(String[] args) {
		
		ListService service = new ListService();
		//service.ex1();
		
		SetService setService = new SetService();
		//setService.ex1();
		//setService.ex2();
		//setService.ex3();
		
		MapService mapService = new MapService();
		//mapService.ex1();
		mapService.ex2();
	}

}
