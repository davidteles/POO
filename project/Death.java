package project;

import event.Event;

public class Death extends Event {
	protected int ID;
	
	public Death(int iD, float instant) {
		super(instant);
		ID = iD;
	}




	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o;
		sim.curr_instant=this.instant;
		
		Individual temp = sim.pop.findID(this.ID);
		sim.pop.individuals.remove(temp);
		sim.pop.setV(sim.pop.individuals.size());
		

	
	}
}
