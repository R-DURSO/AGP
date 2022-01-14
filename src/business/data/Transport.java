package business.data;

public abstract class Transport {
	
	private int price;
	private int comfortLevel;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getComfortLevel() {
		return comfortLevel;
	}
	public void setComfortLevel(int comfortLevel) {
		this.comfortLevel = comfortLevel;
	}
	
	
	
}
