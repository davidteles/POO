package project;

public class Death extends Event {
	protected int ID;
	
	public Death(int iD, int instant) {
		super(instant);
		ID = iD;
	}




	public void realizeEvent() {
		System.out.println("Individual "+ this.ID +" will die in instant "+ this.instant+".");
			
		}
}
