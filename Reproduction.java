package project;

public class Reproduction extends Event {
	protected int ID;

	
	public Reproduction(int iD, int instant) {
		super(instant);
		ID = iD;
		// TODO Auto-generated constructor stub
	}

	public void realizeEvent(Simulation sim) {
		
		sim.curr_instant=this.instant;
		Individual individual = sim.pop.findID(this.ID);
		
		if (individual==null) {
			return;
		}	
		
		int dist_filho= Math.floor(individual.getDistance()*(0.9+0.1*(individual.getConfort())));
		
		int filhoID= (sim.pop.getLast().getID())+1;
		Individual filho= new Individual(sim.pop, filhoID, individual.path.get(dist_filho-1) );
		
		//definir coordenanda
		
		filho.setDistance(dist_filho);
		
		filho.path.clear();
		filho.path.addLast(individual.path.get(0));

		for(int i=1; i<dist_filho; i++) {
			
			filho.path.addLast(individual.path.get(i));
			filho.setTotal_cost(filho.getTotal_cost+sim.zones.getZoneCost(filho.path.get(i-1),filho.path.getZoneCost(i)));
		}
		
		filho.Confort(sim.FindMaxCost, sim.size.x, sim.size.y, sim.pop.k);
		
		//adicionar indivíduo
		sim.pop.addIndividual(filho.curr_pos,sim.FindMaxCost(),sim.size.x, sim.size.y, sim.pop.k);
		
		int nexttime_mfilho = individual.calculateNewMove(individual.getConfort(),sim.pop.r_param)+sim.curr_instant;
		int nexttime_rfilho = individual.calculateNewReproduction(individual.getConfort(),sim.pop.r_param)+sim.curr_instant;
		
		sim.pec.addMove(filhoID, sim.curr_intant + nexttime_mfilho);
		sim.pec.addReproduction(filhoID, sim.curr_intant + nexttime_rfilho);
		//mudar tamanho da população
		
		sim.pop.setV((sim.pop.v)+1);
		
		//definir tempo da proxima reprodução
		
		int nexttime = individual.calculateNewReproduction(individual.getConfort(),sim.pop.r_param)+sim.curr_instant;
		
		if(nexttime<=sim.final_instant) {
			
			sim.pec.addReproduction(individual.getId(), nexttime);
		}		
		
		//Definir epidemia
		if(sim.pop.v > sim.pop.getV_max()) {
			
			Event eve= new Epidemic(0); 
			
			eve.realizeEvent(sim);
		}
	}
			
		}
}
