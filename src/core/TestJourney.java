package core;

import business.data.*;

public class TestJourney {

	public static void main(String[] args) {
		Site site1 = new Site("monument");
		Site site2 = new Site("museum");
		
		Hotel hotel1 = new CheapHotel();
		Hotel hotel2 = new MediumHotel();
		
		Transport walk = new Walk();
		
		Excursion excursion = new Excursion();
		excursion.addHotel(hotel1);
		excursion.addSite(site1);
		excursion.addSite(site2);
		excursion.addTransport(walk);
		excursion.addHotel(hotel2);
		
		excursion.toString();

	}
}
