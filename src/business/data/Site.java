package business.data;

/**
 * A site is place you can visit durong an excursion. It can be an activity, a museum, etc...
 * It can have price or be free.
 */
public class Site {
	private String idSite;
	private String name;
	private int price;
	private int effort;
	private String type;
	private Position position;
	private int duration;	

	public Site() {
		this.name = null;
	}
	
	public Site(String name) {
		this.idSite = "";
		this.name = name;
		this.price = 0;
		this.effort = 0;
		this.type = "";
		this.position = null;
	}
	
	public Site(String id_site, String name, int price, int effort, String type, Position pos, int duration) {
		this.idSite = id_site;
		this.name = name;
		this.price = price;
		this.effort = effort;
		this.type = type;
		this.position = pos;
		this.setDuration(duration);
	}
	
	public Site(String name, int price, int effort, String type, Position pos, int duration) {
		this.name = name;
		this.price = price;
		this.effort = effort;
		this.type = type;
		this.position = pos;
		this.setDuration(duration);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position pos) {
		this.position = pos;
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

	public String getIdSite() {
		return idSite;
	}

	public void setIdSite(String id_site) {
		this.idSite = id_site;
	}

}
