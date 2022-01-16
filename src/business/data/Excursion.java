package business.data;

import java.util.List;

public class Excursion {
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
	
	
}
