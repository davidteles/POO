package project;

import java.util.LinkedList;

public class Population {
	protected int v;
	private int v_max;
	protected Coord init_pos;
	protected Coord fin_pos;
	protected int k;
	LinkedList<Individual> individuals;
	
	
	
	public int getV_max() {
		return v_max;
	}
	public void setV_max(int v_max) {
		this.v_max = v_max;
	}
	
}
