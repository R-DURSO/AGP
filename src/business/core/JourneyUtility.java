package business.core;
import java.util.List;

import business.data.Excursion;
import business.data.Hotel;
import business.data.Position;
import business.data.Site;


/**
 * This class provides utility static methods for simulation.
 */
public class JourneyUtility {
	
	public static Hotel getClosestHotel(List<Hotel> hotelList, Site site) {
		Hotel closest = hotelList.get(0);
		for(Hotel hotel : hotelList) {
			if(distance(site.getPos(), hotel.getPosition()) < distance(site.getPos(), closest.getPosition())) {
				closest = hotel;
			}
		}
		
		return closest;
	}
	
	public static double distance(Position position1, Position position2) {
		return Math.sqrt(Math.pow(position2.getX() - position1.getX(), 2) + Math.pow(position2.getY() - position1.getY(), 2));
	}

	public static int sumEffort(List<Excursion> excursions) {
		int sum = 0;
		for(Excursion ex : excursions) {
			for (Site site : ex.getSiteList()) {
				sum =+ site.getEffort();
			}
		}
		return sum;
	}
	
	public static int sumEffortSite(List<Site> sites) {
		int sum = 0;
			for (Site site : sites) {
				sum =+ site.getEffort();
			}

		return sum;
	}
	
	public static int sumPrice(List<Excursion> excursions) {
		int sum = 0;
		for(Excursion ex : excursions) {
			for (Site site : ex.getSiteList()) {
				sum =+ site.getPrice();
			}
		}
		return sum;
	}
}