package project;

public class Zone {
	
	/*FIELDS*/
	Coord point1, point2; /*duas coordenadas que definem zona especial - dois pontos na diagonal*/
	int cost; /*custo da zona*/
	
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
	
	/*METODOS*/
	
	/*Verifica se uma aresta esta numa zona especial*/
	public int Intersection(Coord input) {
		if(point1.equals(input)) {
			return cost;
		} else if(point2.equals(input)) {
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
	
	/*No caso de interseccao de zonas especias retorna o custo da zona de maior custo*/
	public int getZoneCost(Coord cur_point, Coord next_point){
		int value_1 = this.Intersection(cur_point);
		int value_2 = this.Intersection(next_point);
		
		
		if(value_1 <= value_2) {
			return value_1;
		} else {
			return value_2;
		}
	}

	
}
