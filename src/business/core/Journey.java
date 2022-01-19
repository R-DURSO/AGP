package business.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import business.data.Day;
import business.data.Excursion;
import business.data.Hotel;
import business.data.Site;
import business.data.Transport;

public class Journey {

	private final int EXCURSION_DURATION = 4;
	
	private List<Excursion> journey = new ArrayList<>();
	
	// journeyDuration -> half-day
	private int journeyDuration = 14;
	private int excursionPrice = 0;
	private int budget;
	private int confort;
	private int frequency;
	private int effort;
	
	// Appel à la bd
	private Iterator<Hotel> hotelIterator;
	private Iterator<Transport> transportIterator;
	private Iterator<Site> siteIterator;
	private Iterator<Site> otherSiteIterator;
	
	// Contains hotels corresponding to the user's preferences
	private List<Hotel> hotelList = new ArrayList<>();
	// Contains sites corresponding to the user's preferences
	private List<Site> siteList = new ArrayList<>();
	// Contains sites that DOES NOT correspond to the user's preferences
	private List<Site> otherSiteList = new ArrayList<>();
	
	public void createJourney() {
		hotelIterator.forEachRemaining(hotelList::add);
		siteIterator.forEachRemaining(siteList::add);
		
		// Creation of otherSiteList from the iterator
		while(otherSiteIterator.hasNext()) {
			Site site = otherSiteIterator.next();
			if(!siteList.contains(site)) {
				otherSiteList.add(site);
			}
		}
		Collections.shuffle(otherSiteList);
		
		// Collections.shuffle(siteList);
		
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
			excursion.setDepartureHotel(JourneyUtility.getClosestHotel(hotelList, site));
		} else {
			Hotel lastHotel = journey.get(journey.size() - 1).getArrivalHotel();
			excursion.setArrivalHotel(lastHotel);
		}
		
		// TODO liste de tous les sites si liste de sites prioritaires vide
		if(!siteList.isEmpty()) {
			for(Site site : siteList) {
				if(totalExcursionTime + site.getDuration() <= EXCURSION_DURATION && excursionPrice + site.getPrice() <= budget) {
					excursion.addSite(site);
					excursionPrice += site.getPrice();
					visitedSites.add(site);
				}
			}
		}
		
		// Complete the excursion with other sites if there is still some time left
		for(Site site : otherSiteList) {
			if(totalExcursionTime + site.getDuration() <= EXCURSION_DURATION && excursionPrice + site.getPrice() <= budget) {
				excursion.addSite(site);
				excursionPrice += site.getPrice();
				visitedSites.add(site);
			}
		}
		
		// Delete visited sites
		for(Site site : visitedSites) {
			siteList.remove(site);
		}
		for(Site site : visitedSites) {
			otherSiteList.remove(site);
		}
		
		int nbSites = excursion.getSiteList().size();
		Site lastSite = excursion.getSiteList().get(nbSites - 1);
		excursion.setArrivalHotel(JourneyUtility.getClosestHotel(hotelList, lastSite));
		
		// closest station from departure
		// closest station from sites
		// closest station from arrival
		
		return excursion;
	}
	
	public void linkTransports(List<Excursion> excursions) {
		for(Excursion excursion : excursions) {
			
		}
	}
	
	public List<Excursion> excursionDistribution(List<Excursion> excursions) {
		int priorityNumber = excursions.size();
        int nbEmptyExcursions = journeyDuration - excursions.size();
        for (int it = nbEmptyExcursions; it <= journeyDuration; it++) {
            excursions.add(new Excursion());
        }
        if (confort < 3) {
            Collections.shuffle(excursions);
        } else {
            int averageEffort = JourneyUtility.sumEffort(excursions) * 2 / excursions.size();

            for (int index = priorityNumber; index < excursions.size() - 1; index += 2) {
                int exCurrent = JourneyUtility.sumEffortSite(excursions.get(index).getSiteList());
                int exNext = JourneyUtility.sumEffortSite(excursions.get(index + 1).getSiteList());
                if (exCurrent + exNext > averageEffort) {
                    for (int index2 = index + 1; index2 < excursions.size() - 2; index2++) {
                        int newexNext = JourneyUtility.sumEffortSite(excursions.get(index2).getSiteList());
                        if (exCurrent + newexNext > averageEffort) {
                            Collections.swap(excursions, index + 1, index2);
                        }
                    }
                }
                index = +1;
            }
        }
        return excursions;
    }
	
    public List<Day> createWeek(List<Excursion> excursions){
        List<Day> week = new ArrayList<Day>();
        for (int index = 0 ; index < journeyDuration/2; index +=2) {
            Day newDay = new Day();
            List<Excursion> exDay = new ArrayList<Excursion>();
            exDay.add(excursions.get(index));
            exDay.add(excursions.get(index+1));
            newDay.setExcursionList(exDay);
            // TODO faire la somme du prix 
            newDay.setPrice(JourneyUtility.sumPrice(exDay));
            week.add(newDay);
        }
        return week;
    }
}
