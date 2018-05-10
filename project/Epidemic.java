package project;

/*Importacao dos packages necessarios*/
import java.util.Random;

import event.Event;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;

public class Epidemic extends Event {

	/*CONSTRUCTOR*/
	public Epidemic(float instant) {
		super(instant); /*Instante em que ocorre a epidemia - Instante do evento*/
	}

	/*METODOS*/
	
	/*Realizacao da Epidemia*/
	public void realizeEvent(Object o) {
		Simulation sim = (Simulation) o; /*Cast do objecto recebido para Simulacao*/
		Random random = new Random(); /*Inicializacao de uma variavel aleatoria (observacao aleatoria)*/
		LinkedList<Individual> ind; /*Referencia para a lista de individuos*/
		
		ind = sim.pop.individuals; /*Referencia para a lista de individuos da populacao*/
		Comparator<Individual> cmp1 = new ConfortComparator(); /*Comparador de conforto na lista*/
		
		Collections.sort(ind, cmp1); /*Ordena a lista consoante o conforto (ordem descrescente)*/
		
		float threshold; /*Inicializacao de variavel que e usada como threshold para determinar os
						Individuos que morrem na epidemia*/
		
		/*seleccao dos individuos com menor conforto que vao morrer*/
		for(int i=5; i<ind.size(); i++) { /*ciclo comeca percorrer a lista dos individuos a partir dos
										5 individuos melhores*/
			
			threshold = random.nextFloat(); /*threshold e um valor aleatorio entre 0 e 1*/
			
			/*Se o threshold for maior que o conforto o individuo em questao morre*/
			if(threshold>ind.get(i).getConfort()) {
				sim.pop.individuals.remove(ind.get(i)); /*remove o individuo que morreu da lista*/
			}
		}
		
		Comparator<Individual> cmp2 = new IdComparator(); /*Comparador de ID na lista*/
		
		Collections.sort(ind, cmp2); /*volta a ordenar a lista pelos IDs (ordem crescente)*/
		
		sim.pop.setV(sim.pop.individuals.size()); /*actualiza o numero de individuos que constituem a populacao actual*/
		
	}
				
}

/*INTERFACES*/

/*Comparador que permite ordenadar a lista de individuos pelo conforto por ordem decrescente*/
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

/*Comparador que permite ordenar a lista de individuos pelo ID por ordem crescente*/
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
