package com.example.sghousie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HousieBoard {
	private static List<Integer> generateList = new ArrayList<>();
	
	private static Map<Integer,String> doneNumbers = new ConcurrentHashMap<>(100);
	
	private static Map<Integer,String> lastUpdatedMap;
	
	static {
		init();
	}
	
	public static void main(String[] args) {
		System.out.println(doneNumbers);
	}
	
	private static void init() {
		
		 for(int i=1;i<=90;i++) {
			 doneNumbers.put(i, "false");
		 }
		 addToBoth(1);
		 addToBoth(5);
		 addToBoth(67);
		 addToBoth(78);
	}

	public static void addToBoth(int num) {
		System.out.println("------ adding number : "+num+"------");
		generateList.add(num);
		doneNumbers.put(num, "true");
		updateLastUpdatedMap();
	}
	
	public static Map<Integer,String> getLastUpdatedMap() {
		return lastUpdatedMap;
	}
	
	private static void updateLastUpdatedMap() {
		try {
		lastUpdatedMap = new HashMap<>(105);
		System.out.println(generateList);
		lastUpdatedMap.putAll(doneNumbers);
		//lastUpdatedMap.put(999, generateList.toString());
		int lastIndex=generateList.size()-1;
		
		String str="";
		for(int i =5;lastIndex>=0&&i>0;lastIndex--,i--) {
			str = str + generateList.get(lastIndex) +", ";
		}
		
		lastUpdatedMap.put(998, str);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
