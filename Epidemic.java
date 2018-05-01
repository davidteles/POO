package project;

import java.util.Random;

public class Epidemic extends Event {

	protected float threshold;
	
	
	
	
	
	public Epidemic(int instant) {
		super(instant);
		Random random = new Random();
		this.threshold = random.nextFloat();
	}





	public void realizeEvent(Simulation sim) {
			
	
		
		float[] confort = new float[sim.pop.individuals.size()];
		
		for(int j=0; j<sim.pop.individuals.size(); j++) {
			
			confort[j]=0;
		}
		
		for(int i=0; i<sim.pop.individuals.size(); i++) {
			
			if(sim.pop.individuals.get(i).getConfort()>=confort[i]) {
				
				confort[i]=sim.pop.individuals.get(i).getConfort();
						
					for(int z=i+1; z<sim.pop.individuals.size(); z++) {
							
						confort[z]=confort[z-1];
					}
				}		
			}
		
		for(int j=5; j<sim.pop.individuals.size(); j++) {
			
			if(confort[j] < this.threshold) {
				
				for(int w=0; w<=sim.pop.individuals.size(); w++) {
				  
					if(confort[j]==sim.pop.individuals.get(w).getConfort()) {
					  
					  sim.pop.individuals.remove(w);
					  continue;
				  	}
				  
			  	}
				
			}
		}
		
	}
			
}
