package project;

import java.util.LinkedList;

public class Population {
	
	/*FIELDS*/
	protected int v; //populacao atual - numero atual de individuos - populacao inicial recebida do ficheiro
	private int v_max; //numero max de individuos que pode existir - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected Coord init_pos; //coordenadas da posicao inicial - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected Coord fin_pos; //coordenadas da posicao final - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected int k; //sensibilidade do conforto a pequenas variacoes - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected int d_param; //parametro de morte - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected int r_param; //parametro de reproducao - recebido do ficheiro (CONFIRMAR MODIFIER)
	protected int m_param; //parametro de morte - recebido do ficheiro (CONFIRMAR MODIFIER)
	LinkedList<Individual> individuals; //lista de individuos existentes
	
	/*CONSTRUCTOR*/
	public Population(int vact, int vmax, Coord ini, Coord fin, int sens, int dp, int rp, int mp) {
		this.v = vact;
		this.v_max = vmax;
		this.init_pos = ini;
		this.fin_pos = fin;
		this.k = sens;
		this.d_param = dp;
		this.r_param = rp;
		this.m_param = mp;
		this.individuals = null;
	}
	
	public int getV_max() {
		return v_max;
	}
	public void setV_max(int v_max) {
		this.v_max = v_max;
	}
	
	
	public Individual findID(int id) {
		for(int aux = 0; aux<this.individuals.size() ; aux++) {
			if(this.individuals.get(aux).getId()==id) {
				return this.individuals.get(aux);
			}
		}
		return null;
	}
	
}
