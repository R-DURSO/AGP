package business.data;

public class Site {
	private String description;
	private String name;
	//ajouter la localisation geo ?
	
	public Site() {
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
}
