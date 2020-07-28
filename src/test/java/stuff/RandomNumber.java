package stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {

		for(int i =0;i<20;i+=1) {
			//int st= i;
			//int en=i+9;
			//System.out.println("st: "+st+" - en:"+en);
			System.out.println("===== i: "+i+"====");
			doo(1,90);
			System.out.println("==============\n");
		}
	}
	
	private static void doo(int start,int end) {

		 Random r  = new Random();
		 List<Integer> allNos= new ArrayList<>();
		 List<Integer> selec= new ArrayList<>();
		 for(int i = start;i<=end;i++) {
			 allNos.add(i);
		 }
		 //System.out.println("start: "+start + "end: "+end+"ALL nos: "+allNos);
		 for(int i =0;i<21;i++) {
			int index = r.nextInt(allNos.size()-1);
			
			selec.add(allNos.get(index));
			allNos.remove(index);
		 }
		 Collections.sort(selec);
		 System.out.println("selelc: "+selec);
		 
	
	}

}
