package business.core;
import java.util.List;

import business.data.Excursion;
import business.data.Hotel;
import business.data.Position;
import business.data.Site;


/**
 * This class provides utility static methods for the creation of a journey.
 */
public class JourneyUtility {
	
	public static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}
	
	/**
	 * 
	 * @param hotelList
	 * @param site
	 * @return the closest hotel to site
	 */
	public static Hotel getClosestHotel(List<Hotel> hotelList, Site site) {
		Hotel closest = hotelList.get(0);
		for(Hotel hotel : hotelList) {
			if(distance(site.getPosition(), hotel.getPosition()) < distance(site.getPosition(), closest.getPosition())) {
				closest = hotel;
			}
		}
		
		return closest;
	}
	
	/**
	 * Distance between 2 position points
	 * @param position1
	 * @param position2
	 * @return a distance between 2 points
	 */
	public static double distance(Position position1, Position position2) {
		return Math.sqrt(Math.pow(position2.getX() - position1.getX(), 2) + Math.pow(position2.getY() - position1.getY(), 2));
	}

	/**
	 * 
	 * @param excursions
	 * @return the sum of efforts for the excursion
	 */
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
	
	/**
	 * 
	 * @param excursions
	 * @return sum of the prices of the excursion
	 */
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