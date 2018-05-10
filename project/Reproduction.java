package project;

/*Importacao dos packages necessarios*/
import event.Event;

public class Reproduction extends Event {
	
	/*FIELDS*/
	protected int ID; //IDdo individuo que se vai reproduzir - alvo do evento

	/*CONSTRUCTOR*/
	public Reproduction(int iD, float instant) {
		super(instant); /*Instante no qual o individuo se vai reproduzir - instante do evento*/
		ID = iD; /*ID do individuo*/
	}

	/*METODOS*/
	
	/*Realizacao da reproducao*/
	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o; /*Cats do objecto para Simulation*/
		sim.curr_instant=this.instant; /*O instante actual e actualizado para o instante do evento que ocorre*/
		
		Individual individual = sim.pop.findID(this.ID); /*Procura na lista de individuo o individuo
		 								que se vai reproduzir*/
		if (individual==null) {
			return;
		}	
		
		/*Calcula a disancia do caminho feito pelo filho (que herda parte do caminho do pai)*/
		int dist_filho= (int) Math.ceil(individual.getDistance()*(0.9+0.1*(individual.getConfort())));
		
		int filhoID = (sim.pop.individuals.getLast().getId())+1; /*Determina o ID do filho*/
		Individual filho = new Individual(filhoID, individual.path.get(0)); /*Cria o filho*/
		
		filho.setDistance(dist_filho); /*Set da distancia percorrida pelo filho (herda parte do caminho do pai)*/
		
		if(dist_filho == 0) {
			dist_filho = 1; /*Se a distancia for zero e colocada a um para servi de condicao de 
					paragem no ciclo seguinte*/
		}
	
		
		int cost=1;
		/*Adicionar o caminho herdado ao caminho do filho*/	
		for(int i=1; i<dist_filho; i++) {
				
				filho.path.add(individual.path.get(i));
				cost=1;
				for(int k=0;k<sim.zones.size();k++) { /*Verificacao das zonas de custo especial*/
					if(cost<sim.zones.get(k).getZoneCost(individual.path.get(i-1), individual.path.get(i))) {
						cost=sim.zones.get(k).getZoneCost(individual.path.get(i-1), individual.path.get(i));
					}
				
				}
				filho.setTotal_cost(filho.getTotal_cost()+cost); /*Atualizacao do custo do caminho do filho*/
		}
			
		/*Actualizacao da posicao actual do filho*/
		filho.setCurr_pos(individual.path.get(dist_filho-1));
		/*Actualizacao do conforto e da distancia*/
		filho.SetComfortDistance(sim.FindMaxCost(), sim.size.x, sim.size.y, sim.pop.k, sim.pop.fin_pos);
		
		sim.pop.individuals.add(filho); /*Adicao do filho a lista de individuos*/
		
		/*Calculo do instante em que o novo individuo vai morrer*/
		filho.setDeath_inst(filho.calculateDeath(sim.pop.d_param)+sim.curr_instant);
		/*Se o instante da morte do filho for menor que o instante final da simulacao e adiciona a pec*/
		if(filho.getDeath_inst()<=sim.final_instant) {
			
			sim.pec.addToPEC(new Death(filho.getId(), filho.getDeath_inst()));
		}
		
		/*Calculo do instante em que vai ocorrer o primeiro movimento do filho*/
		float inst = filho.calculateNewMove(sim.pop.m_param)+sim.curr_instant;
		
		if(filho.getDeath_inst()>inst && inst<=sim.final_instant) { /*Se o instante em que o filho 
		se vai mover for menor que o instante da sua morte e que o fim da simulacao o evento e 
		adicionado a pec*/
			sim.pec.addToPEC(new Move(individual.getId(), inst));
			
		}
		
		/*Calculo do instante da primeira reproducao do filho*/
		inst = filho.calculateNewReproduction(sim.pop.r_param)+sim.curr_instant;
		
		if(filho.getDeath_inst()>inst && inst<=sim.final_instant) { /*Se o filho se reproduzir
		antes de morrer e antes da simulacao terminar o evento e adicionado a pec*/
			sim.pec.addToPEC(new Reproduction(filho.getId(), inst));
		}
		
		/*Calculo do instante da proxima reproducao do inviduo pai*/
		inst = individual.calculateNewReproduction(sim.pop.r_param)+sim.curr_instant;
		if(individual.getDeath_inst()>inst && inst<=sim.final_instant) {
			/*Se a proxima reproducao acontecer antes do individuo morre e a simulacao terminar o
			 evento e adicionado a pec*/
			sim.pec.addToPEC(new Reproduction(individual.getId(), inst));
		}
		
		sim.pop.setV(sim.pop.individuals.size()); /*Actualiza o numero de individuos da populacao*/
		
		/*Se o numero de individuos tiver atingido o maximo permitido ocorre um epidemia*/
		if(sim.pop.v > sim.pop.getV_max()) {
			Event eve= new Epidemic(sim.curr_instant); /*Cria o evento epidemia*/
			sim.numberofevents++; /*Incrementa o numero de eventos*/
			eve.realizeEvent(sim); /*Realiza o evento*/
			sim.pop.setV(sim.pop.individuals.size()); /*Actualiza o numero de individuos da populacao*/
		}
		
	}
			
		
}
