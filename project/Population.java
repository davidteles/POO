package project;

/*Importacao do packages necessarios*/
import java.util.LinkedList;

public class Population {
	
	/*FIELDS*/
	protected int v; /*populacao atual - numero atual de individuos - populacao inicial recebida do ficheiro*/
	private int v_max; /*numero max de individuos que pode existir - recebido do ficheiro*/ 
	protected Coord init_pos; /*coordenadas da posicao inicial - recebido do ficheiro*/ 
	protected Coord fin_pos; /*coordenadas da posicao final - recebido do ficheiro*/ 
	protected int k; /*sensibilidade do conforto a pequenas variacoes - recebido do ficheiro*/ 
	protected int d_param; /*parametro de morte - recebido do ficheiro*/ 
	protected int r_param; /*parametro de reproducao - recebido do ficheiro*/ 
	protected int m_param; /*parametro de morte - recebido do ficheiro*/ 
	LinkedList<Individual> individuals; /*lista de individuos existentes*/
	
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
		this.individuals = new LinkedList<Individual>();
	}
	
	/*METODOS*/
	
	/*Get do numero maximo de individuos que podem existir*/
	public int getV_max() {
		return v_max;
	}
	
	/*Set do numero maximo de individuos que podem existir*/
	public void setV_max(int v_max) {
		this.v_max = v_max;
	}
	
	/*Set do numero de individuos existentes*/
	public void setV(int v) {
		this.v = v;
	}
	
	/*Preenche a lista de indiviuos com todos os individuos existentes no inicio*/
	public void addIndividuals(int v, Coord pos, int cmax, int n, int m) {
			
		while(this.individuals.size() != v) {
				
				if(this.individuals.size() != 0) {
					Individual temp = new Individual(this.individuals.getLast().getId()+1, pos);
					temp.SetComfortDistance(cmax, n, m, this.k, this.fin_pos);
					this.individuals.addLast(temp);
					
				}else {
					
					Individual temp = new Individual(1, pos);
					temp.SetComfortDistance(cmax, n, m, this.k, this.fin_pos);
					this.individuals.addLast(temp);
				}
			}
	}
	

	/*Funcao que procura o individio com um determinado ID*/
	public Individual findID(int id) {
		for(int aux = 0; aux<this.individuals.size() ; aux++) {
			if(this.individuals.get(aux).getId()==id) {
				return this.individuals.get(aux);
			}
		}
		return null;
	}
	
}
