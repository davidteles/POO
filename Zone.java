package project;

public class Zone {
	
	/*FIELDS*/
	Coord point1, point2; //duas coordenadas que definem zona especial - dois pontos na diagonal
	int cost; //custo da zona
	
	/*CONSTRUCTOR*/
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
	
	//REVER se vai funcionar como pensado inicialmente
	public int Intersection(Coord input) {
		if(point1.equals(input)) {
			//System.out.println("Equal to point 1");
			return cost;
		} else if(point2.equals(input)) {
			//System.out.println("Equal to point 2");
			return cost;
		} else if(input.x==point1.x || input.x==point2.x){
			if(point1.y<point2.y) {
				if(input.y>=point1.y && input.y<=point2.y) {
					return cost;
				}
			} else {
				if(input.y>=point2.y && input.y<=point1.y) {
					return cost;
				}
			}
		}  else if(input.y==point1.y || input.y==point2.y){
			
			if(input.x>=point1.y && input.x<=point2.y) {
				return cost;
			}
			
		}
		
		return 1;
	}
	
	
	public int getZoneCost(Coord cur_point, Coord next_point){
		int value_1 = this.Intersection(cur_point);
		int value_2 = this.Intersection(next_point);
		
		
		if(value_1 <= value_2) {
			return value_1;
		} else {
			return value_2;
		}
	}
	/*Main para tester zonas*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Coord point_a = new Coord(1,1);
		Coord point_b = new Coord(2,2);
		Coord test_point = new Coord(1,3);
		Zone test = new Zone(point_a,point_b,2);
		System.out.println("Point a cost:"+test.Intersection(point_a));
		System.out.println("Point b cost:"+test.Intersection(point_b));
		System.out.println("Test Point cost:"+test.Intersection(test_point));
	
	}

	
}
