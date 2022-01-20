package business.data;

import java.util.List;

public class Day {
	
	private int price;
	private int comfortLevel;
	private boolean restDay;
	private Excursion morning;
	private Excursion afternoon;

	public Day() {
		this.restDay = false;
		this.morning = null;
		this.afternoon = null;
	}
	
	public Day(boolean isRestDay, Excursion morning, Excursion afternoon) {
		this.restDay = isRestDay;
		this.morning = morning;
		this.afternoon = afternoon;
	}
	
	public void updatePrice() {
		this.price = morning.getPrice() + afternoon.getPrice();
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void updateComfortLevel() {
		this.comfortLevel = morning.getComfortLevel() + afternoon.getComfortLevel();
	}
	
	public int getComfortLevel() {
		return comfortLevel;
	}

	public void setComfortLevel(int comfortLevel) {
		this.comfortLevel = comfortLevel;
	}

	public void setRestDay(boolean restDay) {
		this.restDay = restDay;
	}

	public boolean isRestDay() {
		return restDay;
	}
	
	public String toString() {
		if(restDay) {
			return "This day is a rest day";
		}else {
			String messageString = "";
						messageString = messageString  + "Hotel de départ :" + morning.getDepartureHotel().getName();
						if(morning.getSiteName() == "") {
							messageString= messageString  +"<br /> excursion de la matinée  : repos a la plage ";
						}else{
							messageString= messageString  +"<br /> excursion de la matinée  : "+morning.getSiteName();
						}
						if(morning.getSiteName() == "") {
							messageString= messageString  +"<br /> excursion de l'après midi  : repos a la plage ";
							
						}else {
							messageString= messageString  +"<br /> excursion de l'après midi  : "+afternoon.getSiteName();
						}
						messageString = messageString  + "Hotel d'arrivée :" + afternoon.getArrivalHotel().getName();

			return "This day, you will have 2 excursions composé de : " + messageString;
		}
	}

	public Excursion getMorning() {
		return morning;
	}

	public void setMorning(Excursion morning) {
		this.morning = morning;
	}

	public Excursion getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Excursion afternoon) {
		this.afternoon = afternoon;
	}
}
