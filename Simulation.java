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
	public Simulation(LinkedList<Coord> obs, Coord size, int final_instant, int curr_instant, Coord ini_pos, LinkedList<Zone> zon) {
		
		this.obstacles = obs; //lista de obstaculos e inicializada a null
		this.size = size; //tamanho da grid e dado
		this.final_instant = final_instant; //instante final dado
		this.curr_instant = 0; //instante atual inicialmente e zero
		this.ini_pos = ini_pos; //posicao inicial dada
		this.zones = zon; //lista de zonas especiais inicializada a null
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
		XML parser = new XML();
		parser.ParseXML("C:/Users/RitaC/Documents/2ºsemestre2017_2018/POO/Projecto/data1.xml");
		
		System.out.println("Simulation");
		System.out.println("finalinst= "+parser.var[0]);
		System.out.println("initpop= "+parser.var[1]);
		System.out.println("maxpop= "+parser.var[2]);
		System.out.println("comfortsens= "+parser.var[3]);
		System.out.println("-grid= ");
		System.out.println("colsnb= "+parser.var[4]);
		System.out.println("rowsnb= "+parser.var[5]);
		System.out.println("-initialpoint");
		System.out.println("xinitial= "+parser.var[6]);
		System.out.println("yinitial= "+parser.var[7]);
		System.out.println("-finalpoint");
		System.out.println("xfinal= "+parser.var[8]);
		System.out.println("yfinal= "+parser.var[9]);
		System.out.println("nobs="+parser.var[10]);
		System.out.println("-events");
		System.out.println("death= "+parser.var[11]);
		System.out.println("reproduction= "+parser.var[12]);
		System.out.println("move= "+parser.var[13]);
		System.out.println("zone: xi="+parser.zones.get(0).point1.x+" yi="+parser.zones.get(0).point1.y+" xf="+parser.zones.get(0).point2.x+" yf="+parser.zones.get(0).point2.y+" custo="+parser.zones.get(0).cost);
		System.out.println("Obstacles xpos="+parser.obstacles.getFirst().x+" ypos="+parser.obstacles.getFirst().y);
		
	}

}
