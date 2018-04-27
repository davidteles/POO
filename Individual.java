package project;

import java.util.LinkedList;

public class Individual {
	private int id;
	private int total_cost;
	private int distance;
	private float confort;
	private Coord curr_pos;
	LinkedList<Coord> list;
	
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

	
	
}
