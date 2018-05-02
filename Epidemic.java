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
		Comparator<Individual> cmp1 = new ConfortComparator();
		
		Collections.sort(ind, cmp1);
		
		for(int i=0; i<ind.size(); i++) {
			Random random = new Random();
			
			if(random.nextFloat()>ind.get(i).getConfort()) {
				ind.remove(i);
			}
		}
		
		Comparator<Individual> cmp2 = new IdComparator();
		
		Collections.sort(ind, cmp2);
		
	}
				
}

class ConfortComparator implements Comparator<Individual>{
	public int compare(Individual a, Individual b)
    {
		if(a.getConfort()<b.getConfort()) {
			 return -1;
		 } else if(a.getConfort()>b.getConfort()) {
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
