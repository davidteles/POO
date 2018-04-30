package project;

public abstract class Event {

	int instant;
	
	public Event(int instant) {
		this.instant = instant;
	}
	
	public abstract void realizeEvent(Simulation sim);
	
	 
	
	public static void main(String[] args) {
		Move move = new Move(1,2);
		Death death = new Death(1,3);
		Reproduction reproduction = new Reproduction(1,4);
		Epidemic epidemic = new Epidemic(5);
		System.out.println("Individual "+ move.ID +" will move in instant "+ move.instant + " to the variable "+ move.direction+".");
		System.out.println("Individual "+ death.ID +" will die in instant "+ death.instant+".");
		System.out.println("Individual "+ reproduction.ID +" will reproduce in instant "+ reproduction.instant + ".");
		System.out.println("Every individual with a confort ower than "+ epidemic.threshold + " will diy in instant " +epidemic.instant +".");
	}
}
