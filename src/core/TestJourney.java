package core;

import java.util.ArrayList;
import java.util.List;

import business.data.*;

public class TestJourney {

	public static void main(String[] args) {
		Site site1 = new Site("monument");
		Site site2 = new Site("museum");
		
		Hotel hotel1 = new CheapHotel();
		Hotel hotel2 = new MediumHotel();
		
		Transport walk = new Walk();
		
		List<Site> sitelist=new ArrayList<Site>();
		List<Hotel> hotellist=new ArrayList<Hotel>();
		List<Transport> transportlist=new ArrayList<Transport>();

		Excursion excursion = new Excursion(0,0,sitelist,hotellist,transportlist); 
		excursion.addHotel(hotel1);
		excursion.addSite(site1);
		excursion.addSite(site2);
		excursion.addTransport(walk);
		excursion.addHotel(hotel2);
		
		System.out.println(excursion.toString());

	}
}
