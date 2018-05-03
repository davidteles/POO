package project;

import java.util.LinkedList;

public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; //lista de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	int nobst; //numero de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	Coord size; //tamanho da grid
	int final_instant; //instante final - variavel recebida do ficheiro (CONFIRMAR MODIFIER)
	float curr_instant; //instante atual
	PEC pec; //array de eventos futuros
	LinkedList<Zone> zones; //lista de zonas especiais (com custo diferente de 1) - recebido do ficheiro (CONFIRMAR MODIFIER)
	Population pop; //associacao a populacao 
	LinkedList<Coord> bestpath; //lista que armazena o melhor caminho encontrado
	int bestcost; //custo optimo - custo do melhor caminho
	int numberofevents; //numero de eventos ocorridos
	
	/*CONSTRUCTOR*/
	public Simulation(LinkedList<Coord> obs, int nobs, Coord size, int final_instant, LinkedList<Zone> zon) {
		this.bestpath = new LinkedList<Coord>(); 
		this.bestcost = 0; 
		this.numberofevents=0; 
		this.pec = new PEC(); 
		this.obstacles = obs; //lista de obstaculos e inicializada a null
		this.nobst = nobs; //numero de obstaculos
		this.size = size; //tamanho da grid e dado
		this.final_instant = final_instant; //instante final dado
		this.curr_instant = 0; //instante atual inicialmente e zero
		this.zones = zon; //lista de zonas especiais inicializada a null
		this.pop = null;
		
	}
	
	/*Funca que descobre se numa determinada coordenada existe um obstaculo*/
	public int findObstacle(Coord input) {
		for(int aux = 0; aux<this.obstacles.size() ; aux++) {
			if(this.obstacles.get(aux).equals(input)) {
				return 1;
			}
		}
		return 0;
	}
	
	/*Funcao que descobre o custo maximo que uma aresta pode ter*/
	public int FindMaxCost(){
		int max = 1;
		
		for(int i=0; i<this.zones.size(); i++) {
			if(this.zones.get(i).cost > max) {
				max = this.zones.get(i).cost; 
			}
		}
		return max;
	}
	
	
	public Individual getMoreConfortable() {
		Individual aux = null;
		for(int i=0;i<this.pop.individuals.size();i++) {
			if(aux==null) {
				aux=this.pop.individuals.get(i);
			} else if(aux.getConfort()<this.pop.individuals.get(i).getConfort()){
				aux=this.pop.individuals.get(i);
			}
		}
		
		return aux;
	}
	
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
			
			ind.setDeath_inst(ind.calculateDeath());
			if(ind.getDeath_inst()<=sim.final_instant) {
				sim.pec.addDeath(i+1, ind.getDeath_inst());
			}
			
			float inst = ind.calculateNewMove();
			
			if(ind.getDeath_inst()>inst && inst<=sim.final_instant) {
				sim.pec.addMove(i+1, inst);
			}
			
			inst = ind.calculateNewReproduction();
			
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
