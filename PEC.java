package project;

import java.util.PriorityQueue;
import java.util.Comparator; 



public class PEC {
	
	 PriorityQueue<Event> pec;
	 
	 public PEC() {
		 
		 pec = new PriorityQueue<Event>(99999,new Checker()); 
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
	 
	 public void addStatusUpdate(int instant) {
			
			StatusUpdate update = new StatusUpdate(instant);
			this.pec.add(update);
		 }
	 

	 
	 public Event getNextEvent() {
			
			return this.pec.poll();
		}
	 

	 
	 public static void main(String[] args) {
		 PEC pec = new PEC();
		 pec.addMove(1, 5);
		 pec.addDeath(1, 5);
		 pec.addReproduction(1, 3);
		 
		 for(Event aux = pec.getNextEvent();aux!=null;aux=pec.getNextEvent()) {
			 //aux.realizeEvent(sim);
		 }
		 
		 
		 
	 }
	 
	 
}

class Checker implements Comparator<Event>{
	public int compare(Event a, Event b)
    {
		if(a.instant<b.instant) {
			 return -1;
		 } else if(a.instant>b.instant) {
			 return 1;
		 }
		 return 0;
    }
}