package project;

import java.util.Random;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;

public class Epidemic extends Event {

	public Epidemic(float instant) {
		super(instant);
	}

	public void realizeEvent(Simulation sim) {
			
		LinkedList<Individual> ind; 
		
		ind = sim.pop.individuals;
		Comparator<Individual> cmp1 = new ConfortComparator(); //comparador de conforto na lista
		
		Collections.sort(ind, cmp1); //ordena a lista consoante o conforto (ordem descrescente)
		
		//seleccao dos individuos, com menor conforto, que vao morrer
		for(int i=5; i<ind.size(); i++) {
			Random random = new Random();
			
			if(random.nextFloat()>ind.get(i).getConfort()) {
				ind.remove(i); //removo o individuo que morreu da lista
			}
		}
		
		Comparator<Individual> cmp2 = new IdComparator();
		
		Collections.sort(ind, cmp2); //volta a ordenar a lista pelos IDs (ordem crescente)
		
		sim.pop.v = ind.size(); //actualiza o numero de individuos que constituem a populacao actual
		
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
