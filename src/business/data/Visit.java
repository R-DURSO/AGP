package business.data;

import java.util.List;

public class Visit {

	private int price;
	private int comfortLevel;
	private List<Day> schedule;
	
	public Visit(int price, int comfort, List<Day> schedule) {
		this.comfortLevel=comfort;
		this.price=price;
		this.schedule=schedule;
	}
	
	public void updatePrice() {
		int sum =0;
		for (Day day : schedule) {
			day.updatePrice();
			sum+=day.getPrice();
		}
		this.price=sum;
	}
	
	public void updateComfortLevel() {
		int sum =0;
		for (Day day : schedule) {
			day.updateComfortLevel();
			sum+=day.getComfortLevel();
		}
		this.comfortLevel=sum;
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
	public List<Day> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<Day> schedule) {
		this.schedule = schedule;
	}

	//TODO: modify toString method
	@Override
	public String toString() {
		return "Visit [price=" + price + ", comfortLevel=" + comfortLevel + ", schedule=" + schedule + "]";
	}
	
	
	
}
