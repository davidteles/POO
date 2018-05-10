package project;

//Importacao dos packages necessarios
import event.Event;

public class Sim {

	public static void main(String[] args) {
		
		//Se o numero de argumento recebidos for inferior a 1 sair do programa
		if (args.length < 1){
			System.exit(0);
		}
		
		//String com o nome do ficheiro xml
		String filename = new String(args[0]); 
			
		XML parser = new XML();
		parser.ParseXML(filename); 
		
		//Armazenamento dos dados lidos do ficheiros nas variaveis pretendidas
		Simulation sim = new Simulation(parser.obstacles, parser.var[4], parser.size, parser.var[0], parser.zones);
		
		sim.pop = new Population(parser.var[1], parser.var[2], parser.init, parser.end, parser.var[3], parser.var[5], parser.var[6], parser.var[7]);
		
		Population population = sim.pop; //Referencia para populacao
		
		//Geracao dos primeiros individuos
		population.addIndividuals(population.v, population.init_pos, sim.FindMaxCost(), sim.size.x, sim.size.y);   
		
		//Adiciona os primeiros eventos a PEC
		//Morte, primeiro movimento, primeira reproducao para cada individuo da populacao inicial
		/*eventos sao adicionados a pec se o instante em que vao ocorrer e inferior ao instante final
		 * da simulacao e se a reproduca e movimento antecedem a morte do individuo*/
		for(int i = 0; i<population.individuals.size(); i++) {
			Individual ind = population.individuals.get(i); 
			
			ind.setDeath_inst(ind.calculateDeath(sim.pop.d_param));
			if(ind.getDeath_inst()<=sim.final_instant) {
				sim.pec.addToPEC(new Death(i+1, ind.getDeath_inst()));
				
			}
			
			float inst = ind.calculateNewMove(sim.pop.m_param);
			
			if(ind.getDeath_inst()>inst && inst<=sim.final_instant) {
				sim.pec.addToPEC(new Move(i+1, inst));
				
			}
			
			inst = ind.calculateNewReproduction(sim.pop.r_param);
			
			if(ind.getDeath_inst()>inst && inst<=sim.final_instant) {
				sim.pec.addToPEC(new Reproduction(i+1, inst));
				
			}
			
		}
		
		//Adiciona a pec os evento correspondentes as observacoes da simulacao 
		for(int i = 1; i<=20; i++) {
			sim.pec.addToPEC(new StatusUpdate(i*((int)(sim.final_instant/20))));
			
		}
		
		//Run da PEC
		for(Event aux = sim.pec.getNextEvent();aux!=null;aux=sim.pec.getNextEvent()) {
			 aux.realizeEvent(sim); //realiza evento
			sim.numberofevents++; //incremento o numero de eventos ocorridos
		 } 

	}

}
