package project;

import java.util.LinkedList;

import event.Event;

import pec.PEC;


public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; //lista de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	int nobst; //numero de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	Coord size; //tamanho da grid
	int final_instant; //instante final - variavel recebida do ficheiro (CONFIRMAR MODIFIER)
	float curr_instant; //instante atual
	PEC<Event> pec; //array de eventos futuros
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
		this.pec = new PEC<Event>(); 
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
	

}



