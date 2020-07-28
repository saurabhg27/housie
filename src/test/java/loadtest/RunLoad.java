package loadtest;

import java.util.concurrent.Callable;

import org.springframework.web.client.RestTemplate;

public class RunLoad implements Callable<String>{

	RestTemplate template;
	boolean todo;
	private String url;
	private long sleepTimeMs;
	
	public RunLoad(String url,long sleepTimeMs) {
		this.template = new RestTemplate();
		this.url=url;
		this.sleepTimeMs=sleepTimeMs;
		this.todo = true;
	}
	
	@Override
	public String call() {
		String tName=Thread.currentThread().getName();
		while (todo ) {
			template.getForObject(url, Object.class);
			//System.out.println("Thr: "+tName+" @: "+System.currentTimeMillis());
			//sleep
			try {
				Thread.sleep(sleepTimeMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			//break;
			
		}
		System.out.println("done: "+tName);
		return tName;
	}
	
}