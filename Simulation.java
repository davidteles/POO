package project;

import java.util.LinkedList;

public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; //lista de obstaculos
	Coord size; //tamanho da grid
	int final_instant; //instante final
	int curr_instant; //instante atual
	Coord ini_pos; //posicao inicial
	//PEC pec;
	LinkedList<Zone> zones; //lista de zonas especiais (com custo diferente de 1)
	
	/*CONSTRUCTOR*/
	public Simulation(LinkedList<Coord> obstacles, Coord size, int final_instant, int curr_instant, Coord ini_pos) {
		
		this.obstacles = null; //lista de obstaculos e inicializada a null
		this.size = size; //tamanho da grid e dado
		this.final_instant = final_instant; //instante final dado
		this.curr_instant = 0; //instante atual inicialmente e zero
		this.ini_pos = ini_pos; //posicao inicial dada
		this.zones = null; //lista de zonas especiais inicializada a null
	}

	/*Preenche a lista de zonas especiais*/
	public void addZone(Coord point_a, Coord point_b,int cost) {
		Zone temp = new Zone(point_a,point_b,cost);
		this.zones.addLast(temp);
	}
	
	/*Preenche a lista de obstaculos*/
	public void addZone(int x,int y) {
		Coord temp = new Coord(x,y);
		this.obstacles.addLast(temp);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
