package business.data;

public class Site {
	private String description;
	private String name;
	private int price;
	private int effort; // to define clearly
	private boolean type; // faux si culturel vrai sinon
	private Position pos;
	// ajouter la localisation geo ?

	public Site() {
		this.name = null;
		this.description = null;
	}
	public Site(String name) {
		this.name = name;
		this.description = null;
		this.price = 0;
		this.effort = 0;
		this.type = true;
		this.pos = null;
	}
	public Site(String name, String description, int price, int effort, boolean type, Position pos) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.effort = effort;
		this.type = type;
		this.pos = pos;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
