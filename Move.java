package project;

import java.util.Random;

public class Move extends Event {
	protected float direction;
	protected int ID;
	
	public Move(int iD, int instant) {
		super(instant);
		Random random = new Random();
		ID = iD;
		this.direction = random.nextFloat();
	}
	
	public void realizeEvent() {
		
		
	}
	
}
