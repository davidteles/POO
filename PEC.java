package project;

import java.util.PriorityQueue;;

public class PEC {
	
	 PriorityQueue<Event> pec;
	 
	 public PEC() {
		 
			pec = null; //lista inicialmente a null
	 }

	 
	 public void addMove(int id, int instant) {
			
		Move mov = new Move(id,instant);
		this.pec.add(mov);
	 }
	 
	 public void addReproduction(int id, int instant) {
			
		Reproduction rep = new Reproduction(id,instant);
		this.pec.add(rep);
	 }
	 
	 public void addDeath(int id, int instant) {
			
		Death dea = new Death(id,instant);
		this.pec.add(dea);
	 }
	 
	 
	 public Event getNextEvent() {
			
			return this.pec.poll();
		}
}