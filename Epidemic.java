package project;

import java.util.Random;

public class Epidemic extends Event {

	protected float threshold;
	
	
	
	
	
	public Epidemic(int iD, int instant) {
		super(iD, instant);
		Random random = new Random();
		this.threshold = random.nextFloat();
	}





	public void realizeEvent() {
			
			
		}
}
