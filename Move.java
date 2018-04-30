package project;

import java.util.Random;

public class Move extends Event {
	protected float direction;

	public Move(int iD, int instant) {
		super(iD, instant);
		Random random = new Random();
		this.direction = random.nextFloat();
	}
	
	public void realizeEvent() {
		
		
	}
	
}
