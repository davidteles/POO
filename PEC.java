package project;

public class PEC {
	
	 PriorityQueue<Event> pec;
	 
	 public PEC() {
		 
			pec = null; //lista inicialmente a null
	 }

	 
	 public void addMove(int id, int instant) {
			
		Move mov = new Move();
		this.pec.add(mov);
	 }
	 
	 public void addReproduction(int id, int instant) {
			
		Reproduction rep = new Reproduction();
		this.pec.add(rep);
	 }
	 
	 public void addDeath(int id, int instant) {
			
		Death dea = new Death();
		this.pec.add(dea);
	 }
	 
	 
	 public void removeEvent() {
			
			this.pec.poll();
		}
}