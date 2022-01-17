package business.data;

public class Site {
	private String description;
	private String name;
	private int price;
	private int effort; //to define clearly
	//ajouter la localisation geo ?
	
	public Site() {
		this.name=null;
		this.description=null;
	}
	
	public Site(String name) {
		this.name=name;
		this.description=null;
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
		this.name=name;
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
