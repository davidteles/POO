package project;

//Importacao do package necessario
import event.Event;

public class Death extends Event {
	
	//FIELDS
	protected int ID; //ID do individuo que vai morrer no futuro
	
	//CONSTRUCTOR	
	public Death(int iD, float instant) {
		super(instant); //Instante em que a morte vai ocorrer - instante do evento
		ID = iD; //ID do individuo
	}


	//METODOS
	
	//Realizacao da morte
	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o; //Cast do objecto recebido para simulacao
		sim.curr_instant=this.instant; /*O instante actual da simulacao passa a ser o instante 
									do evento que esta a ocorrer*/
		
		Individual temp = sim.pop.findID(this.ID); //Procura na lista de individuos o individuo alvo
		sim.pop.individuals.remove(temp); //Como o individuo morreu e removido da lista de individuos
		sim.pop.setV(sim.pop.individuals.size()); //Actualiza o tamanho da populacao actual
		
	}
}
