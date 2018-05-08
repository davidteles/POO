package project;

import java.util.Random;

import event.Event;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;

public class Epidemic extends Event {

	public Epidemic(float instant) {
		super(instant);
	}

	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o;
		Random random = new Random();
		LinkedList<Individual> ind; 
		
		ind = sim.pop.individuals;
		Comparator<Individual> cmp1 = new ConfortComparator(); //comparador de conforto na lista
		
		Collections.sort(ind, cmp1); //ordena a lista consoante o conforto (ordem descrescente)
		float threshold;
		//seleccao dos individuos, com menor conforto, que vao morrer
		for(int i=5; i<ind.size(); i++) {
			threshold = random.nextFloat();
			
			if(threshold>ind.get(i).getConfort()) {
				sim.pop.setV(sim.pop.v-1);
				//System.out.println("the individual "+ind.get(i).getId()+" Is Now Dead because is comfort was "+ind.get(i).getConfort()+" and the random was "+threshold);
				sim.pop.individuals.remove(ind.get(i)); //removo o individuo que morreu da lista
			}
		}
		
		Comparator<Individual> cmp2 = new IdComparator();
		
		Collections.sort(ind, cmp2); //volta a ordenar a lista pelos IDs (ordem crescente)
		
		sim.pop.v = sim.pop.individuals.size(); //actualiza o numero de individuos que constituem a populacao actual
		
	}
				
}

class ConfortComparator implements Comparator<Individual>{
	public int compare(Individual a, Individual b)
    {
		if(a.getConfort()>b.getConfort()) {
			 return -1;
		 } else if(a.getConfort()<b.getConfort()) {
			 return 1;
		 }
		 return 0;
    }
}

class IdComparator implements Comparator<Individual>{
	public int compare(Individual a, Individual b)
    {
		if(a.getId()<b.getId()) {
			 return -1;
		 } else if(a.getId()>b.getId()) {
			 return 1;
		 }
		 return 0;
    }
}
