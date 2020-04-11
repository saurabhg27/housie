package com.example.sghousie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HousieBoard {
	private static List<Integer> generateList ;
	
	private static Map<Integer,String> doneNumbers;
	
	private static Map<Integer,String> lastUpdatedMap;
	private static Random random;
	private static String secretKey;
	
	static {
		init();
		reloadfromDisk();
		
		Runnable hook = ()->{saveBoardStatus();};
		Runtime.getRuntime().addShutdownHook(new Thread(hook));
	}
	
	public static void main(String[] args) {
		reloadfromDisk();
		System.out.println(doneNumbers);
	}
	
	private static void init() {
		generateList = new ArrayList<>();
		doneNumbers = new ConcurrentHashMap<>(100);
		lastUpdatedMap = new HashMap<>();
		random = new Random();
		
		secretKey = System.getenv("secretKey");
		if(secretKey==null||secretKey.isBlank()) {
			secretKey = "bla";
		}
		System.out.println("secret key loaded is : "+secretKey);
		 for(int i=1;i<=90;i++) {
			 doneNumbers.put(i, "false");
		 }
		 
		 updateLastUpdatedMap();
	}

	public static synchronized String addToBoth(int num,String key) {
		if(num<1 || num >90) {
			System.out.println("invalid num in add to both : "+num);
			return "ERROR: Number should be between 1 - 90";
		}
		if(!secretKey.equals(key)) {
			return "ERROR: Wrong key entered, jyada shhanpatti nahi ( ͡° ͜ʖ ͡°)";
		}
		System.out.println("------ adding number : "+num+"------");
		if(doneNumbers.get(num).equalsIgnoreCase("true")) {
			return "Number is already added "+ num;
		}
		generateList.add(num);
		doneNumbers.put(num, "true");
		updateLastUpdatedMap();
		return "Added number : "+num;
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

	public static String resetBoard(String key) {
		
		System.out.println("secretkey: "+secretKey+" userKey: "+key);
		String status="Unknown error";
		
		try {
			if(secretKey.equals(key)){
				init();
				status = "reset ho gaya board";
				saveBoardStatus();
			} else {
				status = "key galat hai bhai";
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return status;
	}

	private static void saveBoardStatus() {
		try {
			FileOutputStream fos = new FileOutputStream("board.bin");
			ObjectOutputStream oo = new ObjectOutputStream(fos);
			oo.writeObject(doneNumbers);
			oo.close();
			fos.close();
			
			FileOutputStream fos2 = new FileOutputStream("numberOrder.bin");
			ObjectOutputStream oo2= new ObjectOutputStream(fos2);
			oo2.writeObject(generateList);
			oo2.close();
			fos2.close();
			
			System.out.println("Saved both to disk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void reloadfromDisk() {
		try {
		FileInputStream fis = new FileInputStream("board.bin");
		ObjectInputStream oo = new ObjectInputStream(fis);
		doneNumbers = (Map<Integer, String>) oo.readObject();
		System.out.println("Loaded form disk doneNumbers"+doneNumbers.getClass()+" val --> "+doneNumbers);
		oo.close();
		fis.close();
		
		FileInputStream fis2 = new FileInputStream("numberOrder.bin");
		ObjectInputStream oo1 = new ObjectInputStream(fis2);
		generateList=(List<Integer>) oo1.readObject();
		System.out.println("Loaded form disk generateList"+generateList.getClass()+" val --> "+generateList);
		oo1.close();
		fis2.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			init();
		}
	}

	public static String autoGenerate(String key) {

		List<Integer> remainingNos = doneNumbers.entrySet().stream().filter(e->e.getValue().equalsIgnoreCase("false")).map(e->e.getKey()).collect(Collectors.toList());
		System.out.println("Numbers remaining = "+remainingNos.size());
		if(remainingNos.size()<=0) {
			return "No remaining numbers";
		}
		int num = remainingNos.get(new Random().nextInt(remainingNos.size())); 
		System.out.println("Random number generated is "+num);
		return addToBoth(num, key);
	}

	public static String deleteNumber(Integer number, String key) {
		if(!secretKey.equals(key)) {
			return "ERROR: Wrong key entered, jyada shhanpatti nahi ( ͡° ͜ʖ ͡°)";
		}
		
		if(doneNumbers.get(number).equalsIgnoreCase("false")) {
			return "number is not added yet = " + number;
		}
		doneNumbers.put(number, "false");
		generateList.remove(generateList.indexOf(number));
		System.out.println("deleted number : "+number);
		return "deleted number :"+number;
	}

}
