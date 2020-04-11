package com.example.sghousie;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/")
public class MyController {
	
	Random r = new Random();
	
	 ObjectMapper mapper = new ObjectMapper(); 
	  
	 TypeReference<HashMap<String,String>> typeRef 
	            = new TypeReference<HashMap<String,String>>() {};

	@GetMapping("/test")
	public String lastStatus() {
		int number = r.nextInt(89)+1;
		return ""+number;
	}
	
	@GetMapping("/map")
	public Map<Integer,String> getCompleteStatus(){
		
		return HousieBoard.getLastUpdatedMap();
	}
	
	@PostMapping("/generate")
	public Map<String,String> takeAnInputNumber(@RequestBody String data)	{
		Map<String,String> map = new HashMap<String, String>();

		String statusMessage = "Some error while adding number";

		System.out.println("got data "+data);
		try {
			HashMap<String, String> jsonData = mapper.readValue(data, typeRef);
			String thisTimeNumber = jsonData.get("number");
			if(thisTimeNumber==null||thisTimeNumber.isBlank()) {
				statusMessage = "koi number nahi daala, dhyaan se number daalo bhai";	
			} else {
				Integer numbe = Integer.parseInt(thisTimeNumber);

				String userKey = jsonData.get("key");
				if(userKey==null||userKey.isBlank()) {
					statusMessage = "No Key Found, please put key in box, mazak nahi chal raha idhar ( ͠° ͟ʖ ͡°)";
				} else {
					statusMessage=HousieBoard.addToBoth(numbe,userKey);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		map.put("resp", statusMessage);
		return map;

	}
	
	@PostMapping("/deleteNum")
	public Map<String,String> deleteNum(@RequestBody String data) {
		Map<String,String> map = new HashMap<String, String>();

		String statusMessage = "Some error while auto generating";
		try {
			HashMap<String, String> jsonData = mapper.readValue(data, typeRef);
			String thisTimeNumber = jsonData.get("number");
			
			if(thisTimeNumber==null||thisTimeNumber.isBlank()) {
				statusMessage = "koi number nahi daala, dhyaan se number daalo bhai";	
			} else {
				Integer number = Integer.parseInt(thisTimeNumber);
				String key = jsonData.get("key");
				System.out.println("key is "+key);
				statusMessage=HousieBoard.deleteNumber(number,key);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("resp", statusMessage);
		return map;
		
	}
	
	@PostMapping("/autoGenerate")
	public Map<String,String> autoGenerateNumber(@RequestBody String data) {
		Map<String,String> map = new HashMap<String, String>();

		String statusMessage = "Some error while auto generating";
		try {
			HashMap<String, String> jsonData = mapper.readValue(data, typeRef);
			String key = jsonData.get("key");
			System.out.println("key is "+key);
			statusMessage=HousieBoard.autoGenerate(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("resp", statusMessage);
		return map;

	}
	
	
	@PostMapping("/resetBoard")
	public Map<String,String> resetBoard(@RequestBody String data) {
		Map<String,String> map = new HashMap<String, String>();

		String statusMessage = "Some error while resetting";
		System.out.println("got data "+data);
		try {
			HashMap<String, String> jsonData = mapper.readValue(data, typeRef);
			String key = jsonData.get("key");
			System.out.println("key is "+key);
			
			statusMessage=HousieBoard.resetBoard(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("resp", statusMessage);
		return map;
		
	}
	
}
