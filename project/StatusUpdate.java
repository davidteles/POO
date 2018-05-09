package project;

//Importacao dos packages necessarios
import event.Event;

public class StatusUpdate extends Event {

	//CONTRUCTOR
	public StatusUpdate(float instant) {
		super(instant); //Instante do evento - e feita a verificacao do estado da simulacao
	}

	//Realizacao do evento
	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o; //Cast do objecto recebido para Simulation
		sim.curr_instant=this.instant; //Instante actual e o instante me que o evento ocorre

		//Print das observacoes acerca da simulacao
		System.out.println("Present instant:			"+sim.curr_instant); //Instante actual
		System.out.println("Number of realized events:		"+sim.numberofevents); /*numero de 
															eventos realizados ate ao momento*/
		System.out.println("Population size:			"+sim.pop.individuals.size()); /*Tamanho
		     																actual da populacao*/
		//Se o melhor custo estive a zero nenhum individuo atingiu o ponto final
		if(sim.bestcost==0) {
			System.out.println("Final point has been hit:		NO"); /*Indica que ponto final nao
			 											       		foi atingido*/ 
			
			//Imprime o caminho feito pelo individuo mais confortavel
			System.out.print("Path of the best fit individual:{");
			Individual aux = sim.getMoreConfortable();
			
				if(aux != null) {
					System.out.print(aux.path.get(0).toString());
					for(int i=1;i<aux.path.size();i++) {
						System.out.print(","+aux.path.get(i).toString());
					}
					
					
				}
				System.out.println("}");
				if(aux != null) {
					//Imprime o conforto do individuo mais confortavel
					System.out.println("Cost/Comfort:				"+aux.getConfort());
				}else {
					System.out.println("Cost/Comfort:				0");
				}
				System.out.println("");
				
		} else { //Se o ponto final tiver sido atingido	
			
			System.out.println("Final point has been hit:		YES"); /*Indica que o ponto final foi
			 														atingido*/
			//Imprime o caminho optimo
			System.out.print("Path of the best fit individual:{"); 
			System.out.print(sim.bestpath.get(0).toString());
			for(int i=1;i<sim.bestpath.size();i++) {
				System.out.print(","+sim.bestpath.get(i).toString());
			}
			
			System.out.println("}");
			
			 //Imprime o custo do caminho optimo
			System.out.println("Cost/Comfort:				"+sim.bestcost);
		}
		
		//Se o instante final tiver sido atingido a simulacao termina
		if(this.instant>=sim.final_instant) {
			System.exit(1);
		}

	}

}
