package project;

import event.Event;

public class Sim {

	public static void main(String[] args) {
		
		XML parser = new XML();
		parser.ParseXML("/Users/davidteles/eclipse-workspace/Projeto/src/project/data1.xml"); //Alterar conforme necessario - ATENCAO A ISTO E A DESFORMATACAO DE CARACTER
		
		/*Armazenamento dos dados lidos do ficheiros nas variaveis pretendidas*/
		Simulation sim = new Simulation(parser.obstacles, parser.var[4], parser.size, parser.var[0], parser.zones);
		
		sim.pop = new Population(parser.var[1], parser.var[2], parser.init, parser.end, parser.var[3], parser.var[5], parser.var[6], parser.var[7]);
		
		Population population = sim.pop;
		population.addIndividuals(population.v, population.init_pos, sim.FindMaxCost(), sim.size.x, sim.size.y);   
		
		//Adicionar os primeiros eventos a PEC
		for(int i = 0; i<population.individuals.size(); i++) {
			Individual ind = population.individuals.get(i); 
			
			ind.setDeath_inst(ind.calculateDeath(sim.pop.d_param));
			if(ind.getDeath_inst()<=sim.final_instant) {
				sim.pec.addDeath(i+1, ind.getDeath_inst());
			}
			
			float inst = ind.calculateNewMove(sim.pop.m_param);
			
			if(ind.getDeath_inst()>inst && inst<=sim.final_instant) {
				sim.pec.addMove(i+1, inst);
			}
			
			inst = ind.calculateNewReproduction(sim.pop.r_param);
			
			if(ind.getDeath_inst()>inst && inst<=sim.final_instant) {
				sim.pec.addReproduction(i+1, inst);	
			}
			
		}
		
		for(int i = 1; i<=20; i++) {
			sim.pec.addStatusUpdate(i*((int)(sim.final_instant/20)));
		}
		
		//Run da PEC
		for(Event aux = sim.pec.getNextEvent();aux!=null;aux=sim.pec.getNextEvent()) {
			 aux.realizeEvent(sim);
			sim.numberofevents++;
		 } 

		
	}

}
