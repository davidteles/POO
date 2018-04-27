package project;

import java.util.LinkedList;

public class Individual {
	
	/*FIELDS*/
	private int id; //identificacao do individuo
	private int total_cost; //custo do caminho feito pelo individuo
	private int distance; //distancia percorrida
	private float confort; //conforto atual do individuo
	private Coord curr_pos; //coordenadas da posicao actual do individuo
	LinkedList<Coord> list; //lista com o caminho feito pelo individuo
	Population population; //associacao a classe population - REVER associacao
	
	/*CONSTRUCTOR*/
	public Individual(int Id, Coord pos) {
		list = null; //lista inicialmente a null
		id = Id; //id do individuo logo estabelicido assim que este e criado
		total_cost = 0; //custo inicial e zero
		distance = 0; //ditancia inicial e zero
		confort = 0;//Corigir isto
		curr_pos = pos; //posicao sera a inicial ou o local onde ele nasce
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LinkedList<Coord> getList() {
		return list;
	}

	public void setList(LinkedList<Coord> list) {
		this.list = list;
	}

	//parametro do conforto - menor n
	public int distanceToEnd(Coord current_pos) { 

		int dist_end=0;
		dist_end= (population.fin_pos.x - current_pos.x) + (population.fin_pos.y - current_pos.y);
		return dist_end;
	}
	
	//parametro do conforto - REVER
	public int distanceFromStart(Coord current_pos) {
		
		int dist_start=0;
		dist_start= (current_pos.x - population.init_pos.x) + (current_pos.y - population.init_pos.y);	
		return dist_start;
	}
}
