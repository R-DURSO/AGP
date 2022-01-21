package business.data;

import java.util.ArrayList;
import java.util.List;

public class Excursion {
	private int price;  //might be modified, need to talk about it
	private int comfortLevel;
	
	
	private List<Site> siteList = new ArrayList<Site>();
	private Hotel departureHotel;
	private Hotel arrivalHotel;
	private List<Transport> transportList = new ArrayList<Transport>(); //may be use a hashmap to have a specific transport for every site
	
	public Excursion() {
		
	}
	
	public Excursion(int price, int comfortLevel, List<Site> siteList, Hotel departureHotel, Hotel arrivalHotel, List<Transport> transportList) {
		super();
		this.price = price;
		this.comfortLevel = comfortLevel;
		this.siteList = siteList;
		this.departureHotel = departureHotel;
		this.arrivalHotel = arrivalHotel;
		this.transportList = transportList;
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

	public Hotel getDepartureHotel() {
		return departureHotel;
	}

	public void setDepartureHotel(Hotel departureHotel) {
		this.departureHotel = departureHotel;
	}

	public Hotel getArrivalHotel() {
		return arrivalHotel;
	}

	public void setArrivalHotel(Hotel arrivalHotel) {
		this.arrivalHotel = arrivalHotel;
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
		sum += departureHotel.getPriceLevel();
		sum += arrivalHotel.getPriceLevel();
		for (Transport transport : transportList) {
			sum+=transport.getPrice();
		}
		this.price=sum;
	}
	
	public void updateComfortLevel() {
		int sum =0;
		sum += departureHotel.getComfortLevel();
		sum += arrivalHotel.getComfortLevel();
		for (Transport transport : transportList) {
			sum+=transport.getComfortLevel();
		}
		this.price=sum;
	}
	
	public String getSiteName() {
		if(siteList.isEmpty()) {
			return "--Pas de visites prévues.<br/>";
		}
		String result = "";
		for(Site site : siteList) {
			result += "--" + site.getName() + "<br/>";
		}
		return result;
	}
	
	public String getTransportName() {
		String result = "";
		for(Transport transport : transportList) {
			result += transport.getComfortLevel();
			result += " ";
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Excursion [ sites visités: " + this.getSiteName() + "]";
	}
	
	
}
