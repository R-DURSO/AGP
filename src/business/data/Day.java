package business.data;

import java.util.List;

public class Day {
	
	private int price;
	private int comfortLevel;
	private boolean restDay;
	private List<Excursion> excursionList;

	public Day() {
		this.restDay=false;
		this.excursionList=null;
	}
	
	public Day(boolean isRestDay, List<Excursion> excursionList) {
		this.restDay=isRestDay;
		this.excursionList=excursionList;
	}
	
	public void updatePrice() {
		int sum =0;
		for (Excursion excursion : excursionList) {
			excursion.updatePrice();
			sum+=excursion.getPrice();
		}
		this.price=sum;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void updateComfortLevel() {
		int sum=0;
		for(Excursion excursion : excursionList) {
			excursion.updateComfortLevel();
			sum += excursion.getComfortLevel();
		}
		this.comfortLevel=sum;
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

	public void setExcursionList(List<Excursion> excursionList) {
		this.excursionList = excursionList;
	}

	public List<Excursion> getExcursionList() {
		return excursionList;
	}
	public boolean isRestDay() {
		return restDay;
	}
	
	public String toString() {
		if(restDay) {
			return "This day is a rest day";
		}else {
			String messageString ="";
						messageString = messageString  + excursionList.get(0).getDepartureHotel();
						if(excursionList.get(0).getSiteName() == "") {
							messageString= messageString  +"<br /> excursion de la matinée  : repos a la plage ";
						}else{
							messageString= messageString  +"<br /> excursion de la matinée  : repos a la plage "+excursionList.get(0).getSiteName();
						}
						if(excursionList.get(0).getSiteName() == "") {
							messageString= messageString  +"<br /> excursion de l'après midi  : repos a la plage ";
							
						}else {
							messageString= messageString  +"<br /> excursion de l'après midi  : "+excursionList.get(1).getSiteName();
						}
						messageString = messageString  + excursionList.get(1).getArrivalHotel();

			return "This day, you will have " + this.excursionList.size() + " excursions composé de : "+messageString;
		}
	}
	public void add(Excursion list) {
		excursionList.add(list);
	}
}
