package project;

public class Zone {
	Coord point1, point2;
	int cost;
	
	
	public Zone(Coord a, Coord b, int value) {
		if(a.x<b.x) {
			point1 = a;
			point2 = b;
		} else {
			point1 = b;
			point2 = a;
		}
		
		cost = value;
	}
	
	public int Intersection(Coord input) {
		if(point1.equals(input)) {
			return cost;
		} else if(point2.equals(input)) {
			return cost;
		} else if(input.x>=point1.x && input.x<=point2.x){
			if(point1.y<point2.y) {
				if(input.y>=point1.y && input.y<=point2.y) {
					return cost;
				}
			} else {
				if(input.y>=point2.y && input.y<=point1.y) {
					return cost;
				}
			}
		}
		
		return 0;
	}
}
