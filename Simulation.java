package project;

import java.util.LinkedList;

public class Simulation {
	
	/*FIELDS*/
	LinkedList<Coord> obstacles; //lista de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	int nobst; //numero de obstaculos - recebido do ficheiro (CONFIRMAR MODIFIER)
	Coord size; //tamanho da grid
	int final_instant; //instante final - variavel recebida do ficheiro (CONFIRMAR MODIFIER)
	int curr_instant; //instante atual
	//Coord ini_pos; //posicao inicial - recebido do ficheiro - APAGAR
	//PEC pec;
	LinkedList<Zone> zones; //lista de zonas especiais (com custo diferente de 1) - recebido do ficheiro (CONFIRMAR MODIFIER)
	Population pop; //associacao a populacao 
	
	/*CONSTRUCTOR*/
	public Simulation(LinkedList<Coord> obs, int nobs, Coord size, int final_instant, LinkedList<Zone> zon) {
		
		this.obstacles = obs; //lista de obstaculos e inicializada a null
		this.nobst = nobs; //numero de obstaculos
		this.size = size; //tamanho da grid e dado
		this.final_instant = final_instant; //instante final dado
		this.curr_instant = 0; //instante atual inicialmente e zero
		//this.ini_pos = ini_pos; //posicao inicial dada
		this.zones = zon; //lista de zonas especiais inicializada a null
	}

	public int findObstacle(Coord input) {
		for(int aux = 0; aux<this.obstacles.size() ; aux++) {
			if(this.obstacles.get(aux).equals(input)) {
				return 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XML parser = new XML();
		parser.ParseXML("C:/Users/RitaC/Documents/2�semestre2017_2018/POO/Projecto/data1.xml"); //Alterar conforme necessario - ATENCAO A ISTO E A DESFORMATACAO DE CARACTER
		
		/*Armazenamento dos dados lidos do ficheiros nas variaveis pretendidas*/
		Simulation sim = new Simulation(parser.obstacles, parser.var[4], parser.size, parser.var[0], parser.zones);
		
		sim.pop = new Population(parser.var[1], parser.var[2], parser.init, parser.end, parser.var[3], parser.var[5], parser.var[6], parser.var[7]);
		
		System.out.println("Simulation");
		System.out.println("finalinst= "+sim.final_instant);
		System.out.println("initpop= "+sim.pop.v);
		System.out.println("maxpop= "+sim.pop.getV_max());
		System.out.println("comfortsens= "+sim.pop.k);
		System.out.println();
		System.out.println("-grid");
		System.out.println("--colsnb= "+sim.size.x);
		System.out.println("--rowsnb= "+sim.size.y);
		System.out.println();
		System.out.println("-initialpoint");
		System.out.println("--xinitial= "+sim.pop.init_pos.x);
		System.out.println("--yinitial= "+sim.pop.init_pos.y);
		System.out.println();
		System.out.println("-finalpoint");
		System.out.println("--xfinal= "+sim.pop.fin_pos.x);
		System.out.println("--yfinal= "+sim.pop.fin_pos.y);
		System.out.println();
		System.out.println("-events");
		System.out.println("--death= "+sim.pop.d_param);
		System.out.println("--reproduction= "+sim.pop.r_param);
		System.out.println("--move= "+sim.pop.m_param);
		System.out.println();
		System.out.println("-Zones list size="+parser.zones.size());
		
		for(int i=0; i<parser.zones.size(); i++) {
			System.out.println("zone: xi="+parser.zones.get(i).point1.x+" yi="+parser.zones.get(i).point1.y+" xf="+parser.zones.get(i).point2.x+" yf="+parser.zones.get(i).point2.y+" custo="+parser.zones.get(i).cost);
		}
			
		System.out.println();
		System.out.println("-Obstacles list size="+parser.obstacles.size());
		System.out.println("--nobs="+sim.nobst);
		for(int i=0; i<parser.obstacles.size(); i++) {
			System.out.println("Obstacles xpos="+parser.obstacles.get(i).x+" ypos="+parser.obstacles.get(i).y);
		}
		
		
	}

}
