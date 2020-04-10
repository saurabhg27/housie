package com.example.sghousie;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HousieBoard {
	
	private static Map<Integer,Boolean> doneNumbers = init();
	
	public static void main(String[] args) {
		System.out.println(doneNumbers);
	}
	
	private static Map<Integer, Boolean> init() {
		 Map<Integer, Boolean> map=new ConcurrentHashMap<>(100);
		 for(int i=1;i<=90;i++) {
			 map.put(i, false);
		 }
		 map.put(1,true);
		 map.put(5,true);
		 map.put(23,true);
		 map.put(7,true);
		 map.put(89,true);
		 map.put(56,true);
		return map;
	}

	public static void add(int num) {
		System.out.println("------ adding number : "+num+"------");
		doneNumbers.put(num, true);
	}
	
	public static Map<Integer,Boolean> getMap() {
		return doneNumbers;
	}
	

}
