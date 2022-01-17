package business.data;

import java.util.List;

public class Excursion {
	//TODO:see that with team
	private int price;  //might be modified, need to talk about it
	private int comfortLevel;
	
	
	private List<Site> siteList;
	private List<Hotel> hotelList;
	private List<Transport> transportList; //may be use a hashmap to have a specific transport for every site
	
	
	
	public Excursion(int price, int comfortLevel, List<Site> siteList, List<Hotel> hotelList,
			List<Transport> transportList) {
		super();
		this.price = price;
		this.comfortLevel = comfortLevel;
		this.siteList = siteList;
		this.hotelList = hotelList;
		this.transportList = transportList;
	}



	public void addHotel(Hotel hotel) {
		this.hotelList.add(hotel);
	}
	
	public void addTransport(Transport transport) {
		this.transportList.add(transport);
	}
	
	public void addSite(Site site) {
		this.siteList.add(site);
	}
	
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
	
	public String getSiteName() {
		String result = "";
		for(Site site : siteList) {
			result += site.getName();
			result += " ";
		}
		return result;
	}
	
	@Override
	public String toString() {
		this.updateComfortLevel();
		this.updatePrice();
		return "Excursion [price=" + price + ", comfortLevel=" + comfortLevel + ", sites visités: " + this.getSiteName()
				+ ", hotelList=" + hotelList + ", transportList=" + transportList + "]";
	}
	
	
}
