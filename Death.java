package project;

public class Death extends Event {
	protected int ID;
	
	public Death(int iD, float instant) {
		super(instant);
		ID = iD;
	}




	public void realizeEvent(Simulation sim) {
		sim.curr_instant=this.instant;
		
		Individual temp = sim.pop.findID(this.ID);
		sim.pop.individuals.remove(temp);
		sim.pop.setV(sim.pop.v-1);
		
		if(sim.pop.v > sim.pop.getV_max()) {
			Event eve= new Epidemic(sim.curr_instant); 
			sim.numberofevents++;
			eve.realizeEvent(sim);
		}
	
	}
}
