package project;

import java.util.LinkedList;

public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; //lista de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	int nobst; //numero de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	Coord size; //tamanho da grid
	int final_instant; //instante final - variavel recebida do ficheiro (CONFIRMAR MODIFIER)
	int curr_instant; //instante atual
	PEC pec;
	LinkedList<Zone> zones; //lista de zonas especiais (com custo diferente de 1) - recebido do ficheiro (CONFIRMAR MODIFIER)
	Population pop; //associacao a populacao 
	LinkedList<Coord> bestpath;
	int bestcost;
	
	/*CONSTRUCTOR*/
	public Simulation(LinkedList<Coord> obs, int nobs, Coord size, int final_instant, LinkedList<Zone> zon) {
		this.bestpath = new LinkedList<Coord>();
		this.bestcost = 0;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XML parser = new XML();
		parser.ParseXML("C:/Users/RitaC/Documents/2�semestre2017_2018/POO/Projecto/data1.xml"); //Alterar conforme necessario - ATENCAO A ISTO E A DESFORMATACAO DE CARACTER
		
		/*Armazenamento dos dados lidos do ficheiros nas variaveis pretendidas*/
		Simulation sim = new Simulation(parser.obstacles, parser.var[4], parser.size, parser.var[0], parser.zones);
		
		sim.pop = new Population(parser.var[1], parser.var[2], parser.init, parser.end, parser.var[3], parser.var[5], parser.var[6], parser.var[7]);
		
		Population population = sim.pop;
		population.individuals = new LinkedList<Individual>();
		population.addIndividuals(population.v, population.init_pos, sim.FindMaxCost(), sim.size.x, sim.size.y, population.k);  
		
		System.out.println("Populacao inicial:");
		for(int i = 0; i<population.individuals.size(); i++) {
			
			System.out.println("Invividuo num="+population.individuals.get(i).getId()+" tcost="+population.individuals.get(i).getTotal_cost()+" dist="+population.individuals.get(i).getDistance()+" Confort="+population.individuals.get(i).getConfort()+" pos="+population.individuals.get(i).getCurr_pos().x+" "+population.individuals.get(i).getCurr_pos().y);
			
		}
		
		
		
		
	}

}
