package project;

public class Reproduction extends Event {
	protected int ID;

	
	public Reproduction(int iD, int instant) {
		super(instant);
		ID = iD;
		// TODO Auto-generated constructor stub
	}

	public void realizeEvent() {
		System.out.println("Individual "+ this.ID +" will reproduce in instant "+ this.instant + ".");
			
		}
}
