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
	public void takeAnInputNumber(@RequestBody String data)	{

		System.out.println("got data "+data);
		try {
			HashMap<String, String> jsonData = mapper.readValue(data, typeRef);
			String thisTimeNumber = jsonData.get("number");
			Integer numbe = Integer.parseInt(thisTimeNumber);
			HousieBoard.addToBoth(numbe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
