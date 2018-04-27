package project;

import java.util.LinkedList;

public class Population {
	
	/*FIELDS*/
	protected int v; //populacao atual - nº atual de individuos
	private int v_max; //nº max de individuos que pode existir
	protected Coord init_pos; //coordenadas da posicao inicial
	protected Coord fin_pos; //coordenadas da posicao final
	protected int k; //sensibilidade do conforto a pequenas variacoes
	LinkedList<Individual> individuals; //lista de individuos existentes
	
	public int getV_max() {
		return v_max;
	}
	public void setV_max(int v_max) {
		this.v_max = v_max;
	}
	
}
