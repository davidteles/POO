package project;

public class StatusUpdate extends Event {

	public StatusUpdate(int instant) {
		super(instant);
	}

	
	public void realizeEvent(Simulation sim) {
		sim.curr_instant=this.instant;
		System.out.println("Present instant:				"+sim.curr_instant);
		System.out.println("Number of realized events:		"+sim.numberofevents);
		System.out.println("Population size:				"+sim.pop.v);
		if(sim.bestcost==0) {
			System.out.println("Final point has been hit:		NO");
			System.out.println("Path of the best fit individual:");//TODO
			
			
			
			System.out.println("Cost/Comfort:					");//TODO
		} else {
			System.out.println("Final point has been hit:		YES");
			System.out.println("Path of the best fit individual:");//TODO
			
			
			
			
			System.out.println("Cost/Comfort:					"+sim.bestcost);
		}

	}

}
