package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import business.data.Excursion;
import business.data.Hotel;
import business.data.Site;
import business.data.Transport;

public class Journey {

	private List<Excursion> journey = new ArrayList<>();
	
	// journeyDuration -> half-day
	private int journeyDuration = 14;
	private int excursionDuration = 4;
	private int excursionPrice = 0;
	private int budget;
	private int confort;
	private int frequency;
	private int effort;
	
	private Iterator<Hotel> hotelIterator;
	private Iterator<Transport> transportIterator;
	private Iterator<Site> siteIterator;
	
	private List<Hotel> hotelList = new ArrayList<>();
	private List<Site> siteList = new ArrayList<>();
	
	public void createJourney() {
		hotelIterator.forEachRemaining(hotelList::add);
		siteIterator.forEachRemaining(siteList::add);
		List<Excursion> excursions = new ArrayList<>();
		// 0 < frequency/10 < 1 
		int nbExcursions = frequency / 10 * journeyDuration;
		
		for(int i = 0; i < nbExcursions; i++) {
			excursions.add(createExcursion());
		}
		
		// TODO répartir correctement les excursions sur tout le temps du voyage
		excursions = excursionDistribution(excursions);
	}
		
	public Excursion createExcursion() {
		Excursion excursion = new Excursion();
		int totalExcursionTime = 0;
		List<Site> visitedSites = new ArrayList<>();
		
		if(journey.isEmpty()) {
			Site site = siteList.remove(0);
			excursion.addSite(site);
			excursionPrice += site.getPrice();
			totalExcursionTime += site.getDuration();
			excursion.setDepartureHotel(SejourUtility.getClosestHotel(hotelList, site));
		} else {
			Hotel lastHotel = journey.get(journey.size() - 1).getArrivalHotel();
			excursion.setArrivalHotel(lastHotel);
		}
		
		
		for(Site site : siteList) {
			if(site.getDuration() <= totalExcursionTime - excursionDuration && excursionPrice + site.getPrice() < budget) {
				excursion.addSite(site);
				excursionPrice += site.getPrice();
				visitedSites.add(site);
			}
		}
		
		// Delete visited sites
		for(Site site : visitedSites) {
			siteList.remove(site);
		}
		
		int nbSites = excursion.getSiteList().size();
		Site lastSite = excursion.getSiteList().get(nbSites - 1);
		excursion.setArrivalHotel(SejourUtility.getClosestHotel(hotelList, lastSite));
		
		// closest station from departure
		// closest station from sites
		// closest station from arrival
		
		return excursion;
	}
	
	public List<Excursion> excursionDistribution(List<Excursion> excursions) {
		ArrayList<Excursion> toReturn = new ArrayList<>();
		int nbEmptyExcursions = journeyDuration - excursions.size();
		
		for(int i = 0; i < journeyDuration; i++) {
			
		}
		
		return toReturn;
	}
}
