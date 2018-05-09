package pec;

//Importacao dos packages necessarios
import java.util.PriorityQueue;

import event.Event;

import java.util.Comparator; 


public class PEC {
	 
	//FIELD
	 PriorityQueue<Event> pec; /*Fila de prioridades para organizar os eventos de acordo 
	 						com o instante em que devem ocorrer*/
	 //CONSTRUCTOR
	 public PEC() {
		 pec = new PriorityQueue<Event>( new Checker()); //Cria a fila de prioridades
	 }

	 //METODOS
	 
	 //Adiciona evento a fila
	 public void addToPEC(Event aux) {
		 this.pec.add(aux);
	 }
	 
	 //Retira da fila o evento mais prioritario
	 public Event getNextEvent() {
			
			return this.pec.poll();
		}
	 
}

//INTERFACES

//Interface para estabelecer a prioridade dos eventos
class Checker implements Comparator<Event>{
	public int compare(Event a, Event b)
    {
		if(a.getInstant()<b.getInstant()) {
			 return -1;
		 } else if(a.getInstant()>b.getInstant()) {
			 return 1;
		 }
		 return 0;
    }
}