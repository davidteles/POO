package pec;

import java.util.PriorityQueue;

import event.Event;

import java.util.Comparator; 



public class PEC {
	 
	 PriorityQueue<Event> pec;
	 
	 public PEC() {
		 
		 pec = new PriorityQueue<Event>( new Checker()); 
	 }

	 public void addToPEC(Event aux) {
		 this.pec.add(aux);
	 }
	 

	 public Event getNextEvent() {
			
			return this.pec.poll();
		}
	 

	 

	 
	 
}

class Checker implements Comparator<Event>{
	public int compare(Event a, Event b)
    {
		if(a.getInstant()<b.getInstant()) {
			 return -1;
		 } else if(a.getInstant()>b.getInstant()) {
			 return 1;
		 }
		 return 0;
    }
}