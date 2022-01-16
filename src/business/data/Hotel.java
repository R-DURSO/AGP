package business.data;

public abstract class Hotel {
	
	private int comfortLevel;
	private int priceLevel;
	
	public Hotel() {}
	
	public Hotel(int comfortLevel, int price) {
		this.comfortLevel=comfortLevel;
		this.priceLevel=price;
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
