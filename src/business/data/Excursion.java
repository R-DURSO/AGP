package business.data;

import java.util.List;

public class Excursion {
	//TODO:see that with team
	private int price;  //might be modified, need to talk about it
	private int comfortLevel;
	
	
	private List<Site> siteList;
	private List<Hotel> hotelList;
	private List<Transport> transportList; //may be use a hashmap to have a specific transport for every site
	
	public List<Site> getSiteList() {
		return siteList;
	}
	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}
	public List<Hotel> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	public List<Transport> getTransportList() {
		return transportList;
	}
	public void setTransportList(List<Transport> transportList) {
		this.transportList = transportList;
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
	
	public void updatePrice() {
		int sum =0;
		for (Hotel hotel : hotelList) {
			sum+=hotel.getPriceLevel();
		}
		for (Transport transport : transportList) {
			sum+=transport.getPrice();
		}
		this.price=sum;
	}
	
	public void updateComfortLevel() {
		int sum =0;
		for (Hotel hotel : hotelList) {
			sum+=hotel.getComfortLevel();
		}
		for (Transport transport : transportList) {
			sum+=transport.getComfortLevel();
		}
		this.price=sum;
	}
	@Override
	public String toString() {
		return "Excursion [price=" + price + ", comfortLevel=" + comfortLevel + ", siteList=" + siteList
				+ ", hotelList=" + hotelList + ", transportList=" + transportList + "]";
	}
	
	
}
