package project;

import java.util.LinkedList;
import java.util.Random;

public class Move extends Event {
	protected float direction;
	protected int ID;
	
	public Move(int iD, int instant) {
		super(instant);
		Random random = new Random();
		ID = iD;
		this.direction = random.nextFloat();
	}
	
	@SuppressWarnings("unchecked")
	public void realizeEvent(Simulation sim) {
		sim.curr_instant=this.instant;
		System.out.println("Individual "+ this.ID +" will move in instant "+ this.instant + " to the variable "+ this.direction+".");
		Individual individual = sim.pop.findID(this.ID);
		if (individual==null) {
			return;
		}
		int n_value = 0;
		Coord next;
		int[] n = new int[4];
		Coord curr = individual.getCurr_pos();
		Coord aux = new Coord(0,0);
		
		//Ver em que sitio do mapa esta (
		aux.x=curr.x;
		aux.y=curr.y+1;
		if(aux.y<sim.size.y && sim.findObstacle(aux)==0) {
			n[0]=1;
			n_value++;
		} else {
			n[0]=0;
		}
	
		aux.x=curr.x+1;
		aux.y=curr.y;
		if(aux.x<sim.size.x && sim.findObstacle(aux)==0) {
			n[1]=1;
			n_value++;
		} else {
			n[1]=0;
		}
		

		aux.x=curr.x;
		aux.y=curr.y-1;
		if(aux.y>=0 && sim.findObstacle(aux)==0) {
			n[2]=1;
			n_value++;
		} else {
			n[2]=0;
		}
		aux.x=curr.x-1;
		aux.y=curr.y;
		if(aux.x>=0 && sim.findObstacle(aux)==0) {
			n[3]=1;
			n_value++;
		} else {
			n[3]=0;
		}
		
		
		//Ver para onde é que ele se move
		int i,j,counter=0;
		for(i=0;((i)/n_value<=this.direction && this.direction<(i+1)/n_value);i++) ;
		for(j=0;j<4;j++) {
			if(n[j]==1) {
				counter++;
			}
			if(counter==i) {
				break;
			}
			
		}
		if(j==0) {
			next = new Coord(curr.x,curr.y+1);
		}else if(j==1) {
			next = new Coord(curr.x+1,curr.y);
		}else if(j==2) {
			next = new Coord(curr.x,curr.y-1);
		}else {
			next = new Coord(curr.x-1,curr.y);
		}

		//Ver se ja esta na lista do caminho (Se nao adicionar else remover até esse ponto)
		if(individual.path==null) {
			individual.path.add(next);
		} else {
			for(i=0;i<individual.path.size();i++) {
				
				if(individual.path.get(i).equals(next)) {
					break;
				}
					
			}
			
		}
		if(i==individual.path.size()-1) {
			individual.path.add(next);
			//Add cost,length and update position 
			int cost=1;
			for(int k=0;k<sim.zones.size();k++) {
				if(cost<sim.zones.get(k).getZoneCost(curr, next)) {
					cost=sim.zones.get(k).getZoneCost(curr, next);
				}
			}
			
			
			
			individual.setCurr_pos(next);
			individual.setDistance(individual.getDistance()+1);
			individual.setTotal_cost(individual.getTotal_cost()+cost);
		} else {
			for(j=individual.path.size()-1;j>i;j--) {
				individual.setDistance(individual.getDistance()-1);
				int cost=1;
				for(int k=0;k<sim.zones.size();k++) {
					if(cost<sim.zones.get(k).getZoneCost(individual.path.get(j), individual.path.get(j-1))) {
						cost=sim.zones.get(k).getZoneCost(individual.path.get(j), individual.path.get(j-1));
					}
				}
				individual.path.removeLast();
				individual.setTotal_cost(individual.getTotal_cost()-cost);
				
				
			}
			individual.setCurr_pos(next);
		}
		
		//Atualizar conforto
		individual.Confort(sim.FindMaxCost(), sim.size.x, sim.size.y, sim.pop.k);
		
		int nexttime = individual.calculateNewMove(individual.getConfort(),sim.pop.m_param)+sim.curr_instant;
		if(nexttime<=sim.final_instant) {
			sim.pec.addMove(individual.getId(), nexttime);
		}
		
		
		
		if(individual.getCurr_pos().equals(sim.pop.fin_pos)) {
			if(sim.bestcost==0 || sim.bestcost>individual.getTotal_cost()) {
				sim.bestpath.clear();
				sim.bestpath = (LinkedList<Coord>) individual.path.clone();
				sim.bestcost=individual.getTotal_cost();
			}
		}
		
		
	}
	
}
