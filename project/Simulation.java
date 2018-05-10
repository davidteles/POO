package project;

/*Importacao dos packages necessarios*/
import java.util.LinkedList;



import pec.PEC;

public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; /*lista de obstaculos - recebido do ficheiro*/ 
	int nobst; /*numero de obstaculos - recebido do ficheiro*/ 
	Coord size; /*tamanho da grelha*/
	int final_instant; /*instante final - recebida do ficheiro*/ 
	float curr_instant; /*instante atual*/
	PEC pec; /*array de eventos futuros*/
	LinkedList<Zone> zones; /*lista de zonas especiais (com custo diferente de 1) - recebido do ficheiro*/ 
	Population pop; /*associacao a populacao*/ 
	LinkedList<Coord> bestpath; /*lista que armazena o melhor caminho encontrado*/
	int bestcost; /*custo optimo - custo do melhor caminho*/
	int numberofevents; /*numero de eventos ocorridos*/
	
	/*CONSTRUCTOR*/
	public Simulation(LinkedList<Coord> obs, int nobs, Coord size, int final_instant, LinkedList<Zone> zon) {
		this.bestpath = new LinkedList<Coord>(); /*Cria lista que vai armazenar o caminho optimo*/
		this.bestcost = 0;  /*melhor custo inicializado a zero*/
		this.numberofevents=0; /*numero de eventos ocorridos inicializado a zero*/
		this.pec = new PEC(); /*Cria a pec*/
		this.obstacles = obs; /*lista de obstaculos recebida da informacao retirada do ficheiro*/
		this.nobst = nobs; /*numero de obstaculos*/
		this.size = size; /*tamanho da grelha*/
		this.final_instant = final_instant; /*instante final dado*/
		this.curr_instant = 0; /*instante atual inicialmente e zero*/
		this.zones = zon; /*lista de zonas especiais recebida da informacao retirada do ficheiro*/
		this.pop = null; /*Associacao a populacao inicialmente a null*/
		
	}
	
	/*METODOS*/
	
	/*Verifica se existe obstaculo numa determinada coordenada*/
	public int findObstacle(Coord input) {
		for(int aux = 0; aux<this.obstacles.size() ; aux++) {
			if(this.obstacles.get(aux).equals(input)) {
				return 1;
			}
		}
		return 0;
	}
	
	/*Metodo que descobre o custo maximo que uma aresta pode ter*/
	public int FindMaxCost(){
		int max = 1;
		
		for(int i=0; i<this.zones.size(); i++) {
			if(this.zones.get(i).cost > max) {
				max = this.zones.get(i).cost; 
			}
		}
		return max;
	}
	
	/*Metodo que retorna o individuo mais confortavel*/
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
	

}



