package project;

public class StatusUpdate extends Event {

	public StatusUpdate(float instant) {
		super(instant);
	}

	
	public void realizeEvent(Simulation sim) {
		sim.curr_instant=this.instant;

		System.out.println("Present instant:			"+sim.curr_instant);
		System.out.println("Number of realized events:		"+sim.numberofevents);
		System.out.println("Population size:			"+sim.pop.individuals.size());
		if(sim.bestcost==0) {
			System.out.println("Final point has been hit:		NO");
			System.out.print("Path of the best fit individual:{");
			Individual aux = sim.getMoreConfortable();
			
				if(aux != null) {
					System.out.print(aux.path.get(0).toString());
					for(int i=1;i<aux.path.size();i++) {
						System.out.print(","+aux.path.get(i).toString());
					}
					
					
				}
				System.out.println("}");
				if(aux != null) {
					System.out.println("Cost/Comfort:				"+aux.getConfort());
				
				}
				System.out.println("");
			} else {
				
			System.out.println("Final point has been hit:		YES");
			System.out.print("Path of the best fit individual:{");
			System.out.print(sim.bestpath.get(0).toString());
			for(int i=1;i<sim.bestpath.size();i++) {
				System.out.print(","+sim.bestpath.get(i).toString());
			}
			
			System.out.println("}");
			
			System.out.println("Cost/Comfort:				"+sim.bestcost);
		}
		
		
		if(this.instant>=sim.final_instant) {
			System.exit(1);
		}

	}

}
