package project;

/*Importacao dos packages necessarios*/
import java.util.Random;

import event.Event;

public class Move extends Event {
	
	/*FIELDS*/
	protected float direction; /*Direcao para a qual o individuo se vai mover*/
	protected int ID; /*ID do individuo que se vai mover*/
	
	/*CONSTRUCTOR*/
	public Move(int iD, float instant) {
		super(instant); /*Instante em que o individuo se vai mover - instante do evento*/
		Random random = new Random(); /*Incializacao de variavel aleatoria*/
		ID = iD; /*Inicializacao do ID*/
		this.direction = random.nextFloat(); /*Direcao e definida pela variavel aleatoria ´
						(valor entre 0 e 1) - observacao aleatoria*/
	}
	
	/*METODOS*/
	   
	/*Realizacao do movimento*/
	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o; /*Cats do objecto recebido para simulacao*/
		sim.curr_instant=this.instant; /*Instante actual da simulacao e o instante em que
		 						ocorre o movimento - evento actual*/
		
		Individual individual = sim.pop.findID(this.ID); /*Procura na lista o individuo que se vai*/ 
																							mover*/
		if (individual==null) {
			return;
		}
		
		int n_value = 0; /*Variavel que correspondente ao numero de direcoes que o individuo 
		           pode tomar (inicializada a zero)*/
		Coord next; /*Coordenada para a qual o individuo se ira dirigir*/
		int[] n = new int[4]; /*Flag para determina direccoes possiveis*/
		Coord curr = individual.getCurr_pos(); /*Posicao actual do individuo*/
		Coord aux = new Coord(0,0); /*Coordenada auxiliar para verificar direcoes*/
		
		/*Se o individuo de mover para norte*/
		aux.x=curr.x;
		aux.y=curr.y+1;
		/*Verificar se a norte o individuo se mantem na grelha e se existe algum obstaculo*/
		if(aux.y<=sim.size.y && sim.findObstacle(aux)==0) { 
			n[0]=1; /*Caso a direcao seja possivel flag de norte a 1*/
			n_value++; /*inscrementa numero de direcoes possiveis*/
		} else {
			n[0]=0; /*Caso a direccao seja impossivel de tomar flag a 0*/
		}
	
		/*Verificar se a este o individuo se mantem na grelha e se existe algum obstaculo*/
		aux.x=curr.x+1;
		aux.y=curr.y;
		if(aux.x<=sim.size.x && sim.findObstacle(aux)==0) {
			n[1]=1; /*Caso a direcao seja possivel flag de este a 1*/
			n_value++; /*inscrementa numero de direcoes possiveis*/
		} else {
			n[1]=0; /*Caso a direccao seja impossivel de tomar flag a 0*/
		}
		
		/*Verificar se a sul o individuo se mantem na grelha e se existe algum obstaculo*/
		aux.x=curr.x;
		aux.y=curr.y-1;
		if(aux.y>=1 && sim.findObstacle(aux)==0) {
			n[2]=1; /*Caso a direcao seja possivel flag de sul a 1*/
			n_value++; /*inscrementa numero de direcoes possiveis*/
		} else {
			n[2]=0; /*Caso a direccao seja impossivel de tomar flag a 0*/
		}
		
		/*Verificar se a oeste o individuo se mantem na grelha e se existe algum obstaculo*/
		aux.x=curr.x-1;
		aux.y=curr.y;
		if(aux.x>=1 && sim.findObstacle(aux)==0) {
			n[3]=1; /*Caso a direcao seja possivel flag de oeste a 1*/
			n_value++; /*inscrementa numero de direcoes possiveis*/
		} else {
			n[3]=0; /*Caso a direccao seja impossivel de tomar flag a 0*/
		}
		
		/*Determinar qual das direcoes posiveis a tomar*/
		int i,j,counter=0;
		for(i=0;i<4;i++) {
			/*Determinacao e feito segundo a observacao aleatoria*/
			if(((float)i/(float)n_value)<this.direction && this.direction<=((float)(i+1)/(float)n_value)) {
				break; /*Assim que se ve para que direccao caiu a probabilidade aleatoria sai-se 
						do ciclo - i e um indicador*/
			}	
		}

		/*Verifica qual sera a direccao*/
		for(j=0;j<4;j++) {
			if(n[j]==1) {
				counter++;
				if(counter==i+1) {
					break;
				}
			}
		}
		
		/*De acordo com o indice j determinado seleciona a coordenada seguinte*/
		if(j==0) {
			next = new Coord(curr.x,curr.y+1);
		}else if(j==1) {
			next = new Coord(curr.x+1,curr.y);
		}else if(j==2) {
			next = new Coord(curr.x,curr.y-1);
		}else {
			next = new Coord(curr.x-1,curr.y);
		}
		

		if(individual.path==null) { /*Se o caminho estiver vazio adiciona nova coordenada*/
			individual.path.addLast(next); 
		} else { /*Caso contrario verifica se a coordenada ja foi visitada*/
			for(i=0;i<individual.path.size();i++) {
				
				if(individual.path.get(i).equals(next)) {
					break; /*Se ja tiver sido visitada salta do ciclo - i e o indice do no 
					da lista correspondente a posicao ja visitada = posicao seguinte*/
				}
					
			}
			
		}
		
		if(i==individual.path.size()){ /*Se o indice i for igual ao tamanho do caminho significa 
		que o ciclo anterior foi percorrido e a nova coordenada nao fazia parte ja percorrido*/
			
			individual.path.addLast(next); /*Adiciona a nova coordenada ao caminho*/
			
			/*Verifica se aresta percorrida para a nova posicao tem custo diferente de 1*/
			int cost=1;
			for(int k=0;k<sim.zones.size();k++) {
				
				if(cost<sim.zones.get(k).getZoneCost(curr, next)) {
					cost=sim.zones.get(k).getZoneCost(curr, next);
				}
			}
			
			individual.setCurr_pos(next); /*Actualiza posicao actual*/
			individual.setDistance(individual.getDistance()+1); /*Actualiza distancia percorrida*/
			individual.setTotal_cost(individual.getTotal_cost()+cost); /*Actualizacao do custo total do caminho percorrido*/
		
		} else { /*Caso a nova posicao ja tenha sido visitada*/
			
			/*Percorre a lista do caminho do fim ate a posicao ja visitada*/
			for(j=individual.path.size()-1;j>i;j--) {
				individual.setDistance(individual.getDistance()-1); /*Decrementa a distancia a
				 									medida que anda para tras*/ 
				/*Verifica se as posicao pelo qual passou tinham custo especial para remove-lo*/
				int cost=1;
				for(int k=0;k<sim.zones.size();k++) {
					if(cost<sim.zones.get(k).getZoneCost(individual.path.get(j), individual.path.get(j-1))) {
						cost=sim.zones.get(k).getZoneCost(individual.path.get(j), individual.path.get(j-1));
					}
				}
				individual.path.removeLast(); /*Remove posicoes do caminho a medida que anda para tras*/
				individual.setTotal_cost(individual.getTotal_cost()-cost); /*Diminui o custo a medida que anda para tras*/
					
			}
			
			individual.setCurr_pos(next); /*Actualiza a posicao actual para a coordenada determinada*/
		}
		
		/*Atualiza a distancia e o conforto para a nova posicao ou nova situacao*/
		individual.SetComfortDistance(sim.FindMaxCost(), sim.size.x, sim.size.y, sim.pop.k, sim.pop.fin_pos);
		
		/*Determina o movimento seguinte que o individuo ira realizar*/
		float nexttime = individual.calculateNewMove(sim.pop.m_param)+sim.curr_instant; /*Calculo do instante do proximo movimento*/
		if(nexttime<=sim.final_instant && individual.getDeath_inst()>nexttime) {
		/*Se o novo movimento nao ocorrer depois da morte do individuo ou depois do instante 
		  final da simulacao e adicionado a pec*/
			sim.pec.addToPEC(new Move(individual.getId(), nexttime));
		}
		
		if(individual.getCurr_pos().equals(sim.pop.fin_pos)) {/*Se a posicao actual for o ponto final*/
			/*Verifica se o custo deste individuo que atingiu o ponto final e o melhor*/
			if(sim.bestcost==0 || sim.bestcost>individual.getTotal_cost()) {
				/*Se for o melhor guardo o seu caminh e o custo*/
				sim.bestpath.clear();
				for(i=0;i<individual.path.size();i++) {
					sim.bestpath.addLast(individual.path.get(i));
						
				}
				
				sim.bestcost=individual.getTotal_cost();
			}
		}
		
		
	}
	
}
