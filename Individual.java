package project;

import java.util.LinkedList;

public class Individual {
	
	protected int id;
	protected int total_cost;
	protected int distance;
	protected float confort;
	protected Coord curr_pos;
	LinkedList<Coord> list;
	
	public Individual(int Id, Coord pos) {
		list = null;
		id = Id;
		total_cost = 0;
		distance = 0;
		confort = 0;//Corigir isto
		curr_pos = pos;
		
	}
	
}
