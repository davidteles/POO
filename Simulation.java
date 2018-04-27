package project;

import java.util.LinkedList;

public class Simulation {
	LinkedList<Coord> obstacles;
	Coord size;
	int final_instant;
	int curr_instant;
	Coord ini_pos;
	//PEC pec;
	LinkedList<Zone> zones;
	
	
	
	public Simulation(LinkedList<Coord> obstacles, Coord size, int final_instant, int curr_instant, Coord ini_pos) {
		
		this.obstacles = null;
		this.size = size;
		this.final_instant = final_instant;
		this.curr_instant = 0;
		this.ini_pos = ini_pos;
		this.zones = null;
	}

	public void addZone(Coord point_a, Coord point_b,int cost) {
		Zone temp = new Zone(point_a,point_b,cost);
		this.zones.addLast(temp);
	}

	public void addZone(int x,int y) {
		Coord temp = new Coord(x,y);
		this.obstacles.addLast(temp);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
