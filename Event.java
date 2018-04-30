package project;

public abstract class Event {
	int ID;
	int instant;
	
	public Event(int iD, int instant) {
		ID = iD;
		this.instant = instant;
	}
	
	public abstract void realizeEvent();
	
	
	
	public static void main(String[] args) {
		Move test = new Move(1,2);
		
		
		System.out.println("Individual "+ test.ID +" will move in instant "+ test.instant + " to the variable "+ test.direction);
	}
}
