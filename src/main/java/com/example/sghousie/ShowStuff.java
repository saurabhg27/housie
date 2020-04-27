package com.example.sghousie;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowStuff {

	/*
	 *  
	 * 
	 * 
	 * 
	 */

	private static Map<Integer,List<Integer>> data ;

	public static void main(String[] args) {
		String bi = "7CAD66E1DB941D83CD0DD8196";
		System.out.println(binTodata(bi));
	}

	static {
		data = new HashMap<>();
		data.put(1,Arrays.asList(1,11,21,31,41,51,61,71,81,42,43,4,14,24,34,44,54,64,74,84,6,7,8,86,87,88,17,27,37,47,57,67,77,87,10,20,30,40,50,60,70,90));
		data.put(2,Arrays.asList(12,13,22,23,18,19,28,29,52,63,59,68,74,75,76,77));
		data.put(3,Arrays.asList(1));
	}

	public static void show() {
		Map<Integer,String> map = new HashMap<>();
		Map<Integer,String> doneNumbers = new HashMap<>(105);
		for(int i=1;i<=90;i++) {
			doneNumbers.put(i, "false");
		}

		map.putAll(doneNumbers);	
	}

	private static List<Integer> binTodata(String hexStr) {
		System.out.println("Got hex: "+hexStr);
		List<Integer> activatedNos = new ArrayList<>();
		try {
			int numIndex=1;
			String binary = new BigInteger(hexStr, 16).toString(2);
			for (char ch : binary.toCharArray()) {
				//System.out.println("Number: "+numIndex+" BinFlag: "+ch);
				if(ch=='1')
				{
					activatedNos.add(numIndex);
				}
				numIndex++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return activatedNos;

	}
}
