package project;

//Importacao dos packages necessarios
import java.util.LinkedList;
import java.util.Random;


public class Individual {
	
	//FIELDS
	private int id; //Identificacao do individuo
	private int total_cost; //custo do caminho feito pelo individuo
	private int distance; //distancia percorrida pelo individuo ate ao instante actual
	private float confort; //conforto atual do individuo
	private Coord curr_pos; //coordenadas da posicao actual do individuo
	private float death_inst; //Instante da morte do individuo
	LinkedList<Coord> path; //lista com o caminho feito pelo individuo
	
	
	//CONSTRUCTOR
	public Individual( int idx, Coord pos) {
		
		this.path = new LinkedList<Coord>(); //Cria a lista do caminho
		this.path.addLast(pos); //Coloca no caminho a posicao inicial
		this.id = idx; //indentificacao do individuo	
		this.total_cost = 0;//custo inicial e zero
		this.distance = 0; //ditancia inicial e zero
		this.confort = 0; //Inicializa conforto a zero
		this.curr_pos = pos; //posicao actual do individio e a posicao inicial
		this.death_inst = 0; //instante da morte do individuo inicializado a zero
	}
	
	//METODOS
	
	//Get do instante da morte do individuo
	public float getDeath_inst() {
		return death_inst;
	}

	//Set do instante da morte do individuo
	public void setDeath_inst(float death_inst) {
		this.death_inst = death_inst;
	}

	//Set do conforto e da distancia
	public void SetComfortDistance(int cmax, int n, int m, int k, Coord finalpos) {
		
		int dist = this.path.size()-1; /*actualiza a distancia percorrida pelo individuo de 
								acordo com o caminho feito pelo individuo*/
		float comf = this.Confort(cmax, n, m, k, finalpos); //Calcula e actualiza o conforto 
		
		this.setConfort(comf); //Set do conforto calculado
		this.setDistance(dist); //Set da distancia determinada
	}
	
	//Get do ID do individuo
	public int getId() {
		return id;
	}

	//Get do custo total do caminho feito pelo individuo
	public int getTotal_cost() {
		return total_cost;
	}

	//Set do custo total do caminho feito pelo individuo
	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	//Get da distancia percorrida
	public int getDistance() {
		return distance;
	}

	//Set da distancia percorrida
	public void setDistance(int distance) {
		this.distance = distance;
	}

	//Get do conforto
	public float getConfort() {
		return confort;
	}

	//Set do conforto
	public void setConfort(float confort) {
		this.confort = confort;
	}

	//Get da posicao actual onde se encontra o individuo
	public Coord getCurr_pos() {
		return curr_pos;
	}

	//Set da posicao actual onde se encontra o individuo
	public void setCurr_pos(Coord curr_pos) {
		this.curr_pos = curr_pos;
	}

	//Get do caminho feito pelo individuo
	public LinkedList<Coord> getPath() {
		return path;
	}

	//Set do caminho feito pelo individuo
	public void setPath(LinkedList<Coord> list) {
		this.path = list;
	}

	//Parametro do conforto - menor numero de arestas desde a posicao atual ate a posicao final
	public int distanceToEnd(Coord current_pos, Coord fin_pos) { 
		
		int dist_end = 0;
	
		dist_end= (Math.abs(fin_pos.x- current_pos.x) + Math.abs(fin_pos.y - current_pos.y));
		return dist_end;
	}

	
	//calculo do conforto
	public float Confort (int cmax, int n, int m, int k, Coord finalpos) {
		//cmax - custo maximo que uma aresta pode ter - zonas especiais
		//tcost - custo do caminho feito pelo individuo
		//length - tamanho do caminho que o individuo ja fez
		//dist - menor numero de arestas desde a posicao actual ate ao ponto final
		//n e m - tamanho da grelha, n-x, m-y
		//k - sensibilidade do conforto
		
		float conforto;
		int dist = this.distanceToEnd(this.getCurr_pos(),finalpos);
		
		conforto= (float) (Math.pow(((1.0-((this.getTotal_cost()-this.getDistance() + 2.0)/((cmax-1.0)*this.getDistance() + 3.0)))),k) * Math.pow(((1.0-((dist)/(n + m + 1.0)))),k));
				
		return conforto;
	}
	
	//Calculo do instante da morte
	public float calculateDeath (int d) {
		
		float confort = this.confort;
		int d_param = d;
		float instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = ((1.0-Math.log(1.0-(double)confort))*d_param);
		instance = (float)  (-media*Math.log(1.0-next));
		return instance;		
	}

	//Calculo do instante em que o individuo se vai mover novamente
	public float calculateNewMove (int m) {
		float confort = this.confort;
		int m_param = m;
		
		float instance;
		double media;
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1.0-Math.log((double)confort))*m_param;
		instance = (float) (-media*Math.log(1.0-next));
		return instance;		
	}

	//Calculo do instante em que o individuo se vai reproduzir novamente
	public float calculateNewReproduction (int r) {
	
		float confort = this.confort;
		int r_param = r;
		float instance;
		double media;
		
		Random random=new Random();
		double next= random.nextDouble();
		
		media = (1.0-Math.log((double)confort))*r_param;
		
		instance = (float) (-media*Math.log(1.0-next));
		return instance;		
	}
	


}
