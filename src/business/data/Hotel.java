package business.data;

/**
 * Data class hotel
 */
public abstract class Hotel {
	
	private int comfortLevel;
	private int priceLevel;
	private Position position;
	private String name;
	
	
	public Hotel() {}
	
	public Hotel(int comfortLevel, int price, Position pos, String name) {
		this.comfortLevel=comfortLevel;
		this.priceLevel=price;
		this.position = pos;
		this.name = name;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getComfortLevel() {
		return comfortLevel;
	}
	
	public void setComfortLevel(int comfortLevel) {
		this.comfortLevel = comfortLevel;
	}
	
	public int getPriceLevel() {
		return priceLevel;
	}
	
	public void setPriceLevel(int priceLevel) {
		this.priceLevel = priceLevel;
	}
	
}
