package project;

public class Coord {
	
	/*FIELDS*/
	public int x,y;
	
	/*CONSTRUCTOR*/
	public Coord(int inp_x, int inp_y) {
		x = inp_x;
		y = inp_y;
	}
	
	public void Set(int a,int b) {
		x=a;
		y=b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

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
