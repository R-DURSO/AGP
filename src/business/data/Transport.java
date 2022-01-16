package business.data;

public abstract class Transport {
	
	private int price;
	private int comfortLevel;
	
	public Transport() {
		
	}
	public Transport(int price, int comfortLevel) {
		this.price=price;
		this.comfortLevel=comfortLevel;
	}
	
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
