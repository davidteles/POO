package project;

import java.util.LinkedList;

public class Individual {
	private int id;
	private int total_cost;
	private int distance;
	private float confort;
	private Coord curr_pos;
	LinkedList<Coord> list;
	Population population;
	
	public Individual(int Id, Coord pos) {
		list = null;
		id = Id;
		total_cost = 0;
		distance = 0;
		confort = 0;//Corigir isto
		curr_pos = pos;
		
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

	//parâmetro do conforto
	public int distanceToEnd(Coord current_pos) { 

		int dist_end=0;
		dist_end= (population.fin_pos.x - current_pos.x) + (population.fin_pos.y - current_pos.y);
		return dist_end;
	}
	
	//parâmetro do conforto
	public int distanceFromStart(Coord current_pos) {
		
		int dist_start=0;
		dist_start= (current_pos.x - population.init_pos.x) + (current_pos.y - population.init_pos.y);	
		return dist_start;
	}
}
