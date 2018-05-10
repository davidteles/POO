package event;

public abstract class Event {

	//FIELD
	protected float instant; //Instante em que um evento ocorre
	
	//CONSTRUCTOR
	public Event(float instant) {
		this.instant = instant;
	}
	
	//METODOS
	
	//Get do instante
	public float getInstant() {
		return instant;
	}

	//Set do instante
	public void setInstant(float instant) {
		this.instant = instant;
	}

	//Metodo abstrato de realizacao evento 
	public abstract void realizeEvent(Object o);

	//Override do hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(instant);
		return result;
	}

	//Override do metodo equals para verificar a igualdade de instantes entre eventos
	@Override 
	public boolean equals(Object obj) {
		if (this == obj)	
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (instant != other.instant)
			return false;
		return true;
	} 
	
	
	 
	

}
