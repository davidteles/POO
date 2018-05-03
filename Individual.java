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
	private float death_inst; //Instante da morte do individuo
	LinkedList<Coord> path; //lista com o caminho feito pelo individuo
	Population population; //associacao a classe population - REVER associacao
	
	/*CONSTRUCTOR*/
	public Individual(Population pop, int idx, Coord pos) {
		
		this.population = pop;
		this.path = new LinkedList<Coord>(); //lista inicialmente a null
		this.path.addLast(pos);
		this.id = idx; //indentificacao do individuo	
		this.total_cost = 0;//custo inicial e zero
		this.distance = 0; //ditancia inicial e zero
		this.confort = 0; //calculo do conforto logo assim que o individuo e criado
		this.curr_pos = pos; //posicao sera a inicial ou o local onde ele nasce
		this.death_inst = 0;
	}
	
	public float getDeath_inst() {
		return death_inst;
	}

	public void setDeath_inst(float death_inst) {
		this.death_inst = death_inst;
	}

	public void SetComfortDistance(int cmax, int n, int m) {
		
		int dist = this.path.size()-1;
		float comf = this.Confort(cmax, n, m, this.population.k);
		
		this.setConfort(comf);
		this.setDistance(dist);
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
	
		dist_end= (Math.abs(this.population.fin_pos.x- current_pos.x) + Math.abs(this.population.fin_pos.y - current_pos.y));
		return dist_end;
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
	public float Confort (int cmax, int n, int m, int k) {
		//cmax - vem do custo maximo que uma aresta pode ter - ir buscar as zonas especiais - pode ser uma variavel que se guarda logo
		//tcost - vem do individuo
		//length - tamanho do caminho que o individuo ja fez
		//dist - menor numero de arestas ate ao ponto final
		//n e m - tamanho da grid, n-x, m-y
		//k - ir buscar a population
		
		float conforto;
		int dist = this.distanceToEnd(this.getCurr_pos());
		
		conforto= (float) (Math.pow(((1.0-((this.getTotal_cost()-this.getDistance() + 2.0)/((cmax-1.0)*this.getDistance() + 3.0)))),k) * Math.pow(((1.0-((dist)/(n + m + 1.0)))),k));
				
		return conforto;
	}
	
	public float calculateDeath () {
		
		float confort = this.confort;
		int d_param = this.population.d_param;
		float instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = ((1.0-Math.log(1.0-(double)confort))*d_param);
		instance = (float)  (-media*Math.log(1.0-next));
		return instance;		
	}

	public float calculateNewMove () {
		float confort = this.confort;
		int m_param = this.population.m_param;
		
		float instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1.0-Math.log((double)confort))*m_param;
		instance = (float) (-media*Math.log(1.0-next));
		return instance;		
	}

	public float calculateNewReproduction () {
	
		float confort = this.confort;
		int r_param = this.population.r_param;
		float instance;
		double media;
		
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1.0-Math.log((double)confort))*r_param;
		
		instance = (float) (-media*Math.log(1.0-next));
		return instance;		
	}
	
	public static void main(String[] args) {
		Coord pos = new Coord(0,0);
		Coord pos_final = new Coord(8,8);
		Population pop = new Population(1,10,pos,pos_final,2,2,2,2);
		Individual individual = new Individual(pop,1,pos);
		individual.SetComfortDistance(1, 10, 10);
		System.out.println("Confort:"+individual.getConfort());
		float temp = individual.calculateNewMove();
		System.out.println("Move time:"+temp);
		temp = individual.calculateNewReproduction();
		System.out.println("Reproduction time:"+temp);
		temp = individual.calculateDeath();
		System.out.println("Death time:"+temp);
	}


}
