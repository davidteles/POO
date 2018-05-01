package project;

import java.util.LinkedList;
import java.util.Random;


public class Individual {
	
	/*FIELDS*/
	private int id; //identificacao do individuo
	private int total_cost; //custo do caminho feito pelo individuo
	private int distance; //distancia percorrida
	private float confort; //conforto atual do individuo
	private Coord curr_pos; //coordenadas da posicao actual do individuo
	LinkedList<Coord> path; //lista com o caminho feito pelo individuo
	Population population; //associacao a classe population - REVER associacao
	
	/*CONSTRUCTOR*/
	public Individual(Population pop, int idx, Coord pos, int cmax, int n, int m, int k) {
		
		this.population = pop;
		this.path = null; //lista inicialmente a null
		this.id = idx; //indentificacao do individuo	
		this.total_cost = 0;//custo inicial e zero
		this.distance = 0; //ditancia inicial e zero
		this.confort = this.Confort(cmax, 0, 0, this.distanceToEnd(pos), n, m, k); //calculo do conforto logo assim que o individuo e criado
		this.curr_pos = pos; //posicao sera a inicial ou o local onde ele nasce
	}
	
	public int getId() {
		return id;
	}


	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public float getConfort() {
		return confort;
	}

	public void setConfort(float confort) {
		this.confort = confort;
	}

	
	public Coord getCurr_pos() {
		return curr_pos;
	}

	public void setCurr_pos(Coord curr_pos) {
		this.curr_pos = curr_pos;
	}

	public LinkedList<Coord> getPath() {
		return path;
	}

	public void setPath(LinkedList<Coord> list) {
		this.path = list;
	}

	//parametro do conforto - menor numero de arestas desde a posicao atual ate a posicao final
	public int distanceToEnd(Coord current_pos) { 
		
		int dist_end = 0;
	
		dist_end= (Math.abs(population.fin_pos.x - current_pos.x)) + (Math.abs(population.fin_pos.y - current_pos.y));
		return dist_end;
	}
	
	//parametro do conforto - REVER
	public int distanceFromStart(Coord current_pos) {
		
		int dist_start=0;
		dist_start= (current_pos.x - population.init_pos.x) + (current_pos.y - population.init_pos.y);	
		return dist_start;
	}
	
	//Adicionar ponto ao caminho do individuo
	public void addPath(int x,int y) {
		
		Coord temp = new Coord(x,y);
		this.path.addLast(temp);
		
	}
	
	//Remover ponto do caminho do indivduo (no método movimento fazemos as condicoes para retirar os elementos quando 
	//o indivíduo chega a um ponto que já pertence ao caminho
	public void removePath() {
		
		this.path.removeLast();
	}
	
	//calculo do conforto, tem erros porque ainda nao definimos k,cmax, n e m (tipo static final)
	public float Confort (int cmax, int tcost, int length, int dist, int n, int m, int k) {
		//cmax - vem do custo maximo que uma aresta pode ter - ir buscar as zonas especiais - pode ser uma variavel que se guarda logo
		//tcost - vem do individuo
		//length - tamanho do caminho que o individuo ja fez
		//dist - menor numero de arestas ate ao ponto final
		//n e m - tamanho da grid, n-x, m-y
		//k - ir buscar a population
		
		float conforto=0;
		
		conforto= ((1-((tcost-length + 2)/((cmax-1)*length + 3)))^k) * ((1-((dist)/(n + m + 1)))^k);
				
		return conforto;
	}
	
	public int calculateDeath (float confort, int d_param) {
		
		int instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = ((1-Math.log(1-(double)confort))*d_param);
		instance = (int) Math.floor((-media*Math.log(1-next)));
		return instance;		
	}

	public int calculateNewMove (float confort, int m_param) {
	
		int instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1-Math.log((double)confort))*m_param;
		instance = (int) Math.floor((-media*Math.log(1-next)));
		return instance;		
	}

	public int calculateNewReproduction (float confort, int r_param) {
	
		int instance;
		double media;
		
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1-Math.log((double)confort))*r_param;
		
		instance = (int) Math.floor((-media*Math.log(1-next)));
		return instance;		
	}
}
