package test;

import java.util.ArrayList;
import java.util.List;

import business.data.*;

public class TestJourney {

	public static void main(String[] args) {
		Site site1 = new Site("monument");
		Site site2 = new Site("museum");
		
		Hotel hotel1 = new CheapHotel(new Position(0, 0),"proche de la place ");
		Hotel hotel2 = new MediumHotel(new Position(1, 1),"proche du port ");
		
		Transport walk = new Walk(10);
		
		List<Site> sitelist=new ArrayList<Site>();
		List<Hotel> hotellist=new ArrayList<Hotel>();
		List<Transport> transportlist=new ArrayList<Transport>();

		Excursion excursion = new Excursion(0,0,sitelist,hotel1,hotel2,transportlist); 
		excursion.setArrivalHotel(hotel1);
		excursion.addSite(site1);
		excursion.addSite(site2);
		excursion.addTransport(walk);
		excursion.setDepartureHotel(hotel2);
		
		System.out.println(excursion.toString());

	}
}
