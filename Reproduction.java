package project;

public class Reproduction extends Event {
	protected int ID;

	
	public Reproduction(int iD, float instant) {
		super(instant);
		ID = iD;
		// TODO Auto-generated constructor stub
	}

	public void realizeEvent(Simulation sim) {
		sim.curr_instant=this.instant;
		//System.out.println("Individual "+ this.ID +" will reproduce in instant "+ this.instant+".");

		Individual individual = sim.pop.findID(this.ID);
		
		if (individual==null) {
			//System.out.println("Indiviual "+this.ID + " is already dead.");
			return;
		}	
		
		int dist_filho= (int) Math.ceil(individual.getDistance()*(0.9+0.1*(individual.getConfort())));
		System.out.println("dist_filho="+dist_filho);
		
		//System.out.println("Son Distance: "+dist_filho);
		int filhoID = (sim.pop.individuals.getLast().getId())+1;
		Individual filho= new Individual(sim.pop, filhoID, individual.path.get(0));
		
		filho.setDistance(dist_filho);
		
		if(dist_filho == 0) {
			dist_filho = 1;
		}
	
		//definir coordenanda
		
		int cost=1;
		if(dist_filho != 0) {
			for(int i=1; i<dist_filho; i++) {
				
				filho.path.add(individual.path.get(i));
				cost=1;
				for(int k=0;k<sim.zones.size();k++) {
					if(cost<sim.zones.get(k).getZoneCost(individual.path.get(i-1), individual.path.get(i))) {
						cost=sim.zones.get(k).getZoneCost(individual.path.get(i-1), individual.path.get(i));
					}
				
				}
				filho.setTotal_cost(filho.getTotal_cost()+cost);
		}
		
		filho.setCurr_pos(individual.path.get(dist_filho-1));
		filho.SetComfortDistance(sim.FindMaxCost(), sim.size.x, sim.size.y);
		//adicionar individuo
		//System.out.println("Indiviual "+filho.getId()+ " was born.");
		sim.pop.addIndividual(filho.getCurr_pos(),sim.FindMaxCost(),sim.size.x, sim.size.y, sim.pop.k);
		
		filho.setDeath_inst(filho.calculateDeath()+sim.curr_instant);
		if(filho.getDeath_inst()<=sim.final_instant) {
			sim.pec.addDeath(filho.getId(), filho.getDeath_inst());
		}
		
		float inst = filho.calculateNewMove()+sim.curr_instant;
		
		if(filho.getDeath_inst()>inst && inst<=sim.final_instant) {
			sim.pec.addMove(filho.getId(), inst);
		}
		
		inst = filho.calculateNewReproduction()+sim.curr_instant;
		
		if(filho.getDeath_inst()>inst && inst<=sim.final_instant) {
			sim.pec.addReproduction(filho.getId(), inst);	
		}
		
		//mudar tamanho da populacao
		
		sim.pop.setV((sim.pop.v)+1);
		
		//definir tempo da proxima reprodução
		
		inst = individual.calculateNewReproduction()+sim.curr_instant;
		if(individual.getDeath_inst()>inst) {
			sim.pec.addReproduction(individual.getId(), inst);
		}

		//Definir epidemia
		if(sim.pop.v > sim.pop.getV_max()) {
			Event eve= new Epidemic(sim.curr_instant); 
			sim.numberofevents++;
			eve.realizeEvent(sim);
		}
	}
			
		}
}
