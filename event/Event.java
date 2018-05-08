package event;


public abstract class Event {

	protected float instant;
	
	public Event(float instant) {
		this.instant = instant;
	}
	
	
	
	public float getInstant() {
		return instant;
	}



	public void setInstant(float instant) {
		this.instant = instant;
	}



	public abstract void realizeEvent(Object o);



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(instant);
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
		Event other = (Event) obj;
		if (instant != other.instant)
			return false;
		return true;
	}
	
	
	 
	

}
