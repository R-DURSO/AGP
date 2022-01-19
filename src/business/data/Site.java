package business.data;

public class Site {
	private String name;
	private int price;
	private int effort; // to define clearly
	private boolean type; // faux si culturel vrai sinon
	private Position pos;
	private int duration;
	// ajouter la localisation geo ?
	

	public Site() {
		this.name = null;
	}
	
	public Site(String name) {
		this.name = name;
		this.price = 0;
		this.effort = 0;
		this.type = true;
		this.pos = null;
	}
	
	public Site(String name, int price, int effort, boolean type, Position pos, int duration) {
		this.name = name;
		this.price = price;
		this.effort = effort;
		this.type = type;
		this.pos = pos;
		this.setDuration(duration);
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
