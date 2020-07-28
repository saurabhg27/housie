package loadtest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadTest {

	
	public static void main(String[] args) {
		System.out.println("Start");

		String url="http://saurabhgaggar.com/housie/map";
		long sleepTimeMs=1;
		
		
		int threadNum = 1000;
		ExecutorService pool = Executors.newFixedThreadPool(threadNum );
		Set<RunLoad> tasks = new HashSet<>();
		for (int i = 0; i < threadNum; i++) {
			tasks.add(new RunLoad(url, sleepTimeMs));	
		}
		
		
		try {
			pool.invokeAll(tasks);
			System.out.println("Submitted");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("End");
	}
	
	

}
