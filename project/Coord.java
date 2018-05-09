package project;

public class Coord {
	
	//FIELDS
	public int x,y; //Coordenadas de uma posicao na grelha
	
	//CONSTRUCTOR
	public Coord(int inp_x, int inp_y) {
		x = inp_x; //Inicializa a coordenada x com um determinado valor
		y = inp_y; //Inicializa a coordenada y com um determinado valor
	}
	
	//METODOS
	
	//Set das coordenadas
	public void Set(int a,int b) {
		x=a; 
		y=b;
	}

	//Override do metodo toString para representar as coordenadas como pretendido 
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	//Override do metodo hasjcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	//Override do metodo equals para verificar a igualdade de coordenadas
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	
}
