package business.data;

public abstract class Transport {
	
	private int price;
	private int comfortLevel;
	private int speed;
	
	public Transport() {
		
	}
	public Transport(int price, int comfortLevel, int speed) {
		this.price=price;
		this.comfortLevel=comfortLevel;
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
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
