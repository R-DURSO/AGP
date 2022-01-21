package business.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import business.data.Excursion;
import business.data.Hotel;
import business.data.JourneyCritere;
import business.data.Site;
import business.data.SiteScore;
import persistence.jdbc.JdbcPersistence;

/**
 * Journey manages the creation of the whole journey which consists of multiple excursions.
 * It also creates the excursions.
 */
public class Journey {

	JdbcPersistence persistence = new JdbcPersistence();
	JourneyCritere critere ;
	private final int EXCURSION_DURATION = 4;
	private List<Excursion> journey = new ArrayList<>();
	
	// journeyDuration in half-days
	private int journeyDuration = 14;
	private int journeyPrice = 0;
	private int budget;
	private int confort;
	private int frequency;
	private String keyWord;
	
	// Iterators that comes from the db
	private Iterator<Hotel> hotelIterator;
	private Iterator<SiteScore> siteIterator;	
	private Iterator<Site> otherSiteIterator;
	
	// Lists
	// Contains hotels corresponding to the user's preferences
	private List<Hotel> hotels;
	// Contains sites corresponding to the user's preferences
	private List<Site> sites;
	// Contains sites that DOES NOT correspond to the user's preferences
	private List<Site> otherSites;
	
	public Journey(JourneyCritere critere) {
		this.critere = critere;
		this.budget = critere.getPrice();
		this.confort = critere.getConfortChoice();
		this.frequency = critere.getFrequency();
		critere.getEffortLevel();
	}
	
	public Journey() {
	}
	
	/**
	 * Initialise the criterions and get data from the db
	 * @param critere
	 */
	public void init(JourneyCritere critere) {
		this.critere = critere;
		this.budget = critere.getPrice();
		this.confort = critere.getConfortChoice();
		this.frequency = critere.getFrequency();
		this.keyWord = critere.getKeyWord();
		
		String[] words = keyWord.split(" ");
		
		siteIterator = persistence.getTouristAttractionWithKeyWord(words[0], words[1]);
		otherSiteIterator = persistence.allTouristAttractions();
		hotelIterator = persistence.getAllHotel(confort);
		
		hotels = new ArrayList<>();
		sites = new ArrayList<>();
		otherSites = new ArrayList<>();
		
		// Convert iterators into lists
		hotelIterator.forEachRemaining(hotels::add);
		siteIterator.forEachRemaining(sites::add);
		otherSiteIterator.forEachRemaining(otherSites::add);
		
		createJourney();
	}

	/**
	 * Creation of the journey. Create multiple excursions some with sites and some without depending on the frequency specified by the user
	 * Shuffle the order of excursions so the journey is more homogeneous
	 */
	public void createJourney() {
		Collections.shuffle(otherSites);
		
		// Create excursions with visits
		List<Excursion> excursions = new ArrayList<Excursion>();
		// 0 < frequency/10 < 1 
		double frequencyFactor = (double) frequency / 10;
		int nbExcursions = (int) Math.round(frequencyFactor * journeyDuration);
		for(int i = 0; i < nbExcursions; i++) {
			excursions.add(createExcursion());
		}
		
		// Create the remaining empty excursions to complete the journey
		int nbEmptyExcursions = journeyDuration - excursions.size();
		for (int i = 0; i < nbEmptyExcursions; i++) {
            excursions.add(new Excursion());
        }
		
		// Distribute the excursions and empty excursions "randomly"
		excursions = excursionDistribution(excursions);
		
		// Add hotels
		linkHotels(excursions);
		
		journey = excursions;
	}
	
	/**
	 * Create a single excursion. We create excursion with sites that match the user's keywords (in sites) in priority. If needed we use
	 * otherSites which contains sites that do not match the keywords
	 * @return an excursion with potentially multiple sites
	 */
	public Excursion createExcursion() {
		Excursion excursion = new Excursion();
		int totalExcursionTime = 0;
		List<Site> visitedSites = new ArrayList<>();
		
		// Do this when there are still matching sites remaining
		if(!sites.isEmpty()) {
			for(Site site : sites) {
				if(totalExcursionTime + site.getDuration() <= EXCURSION_DURATION && journeyPrice + site.getPrice() <= budget) {
					excursion.addSite(site);
					journeyPrice += site.getPrice();
					totalExcursionTime += site.getDuration();
					visitedSites.add(site);
				}
			}
		}
		
		// Complete the excursion with other sites if there is still some time left
		for(Site site : otherSites) {
			if(totalExcursionTime + site.getDuration() <= EXCURSION_DURATION && journeyPrice + site.getPrice() <= budget) {
				excursion.addSite(site);
				journeyPrice += site.getPrice();
				totalExcursionTime += site.getDuration();
				visitedSites.add(site);
			}
		}
		
		// Delete visited sites
		for(Site site : visitedSites) {
			sites.remove(site);
		}
		for(Site site : visitedSites) {
			otherSites.remove(site);
		}
		
		return excursion;
	}
	
	/**
	 * Add departure and arrival hotels to each excursion. The departure hotel from an excursion is the arrival hotel from the last excursion.
	 * The arrival hotel is the closest hotel to the last visited site.
	 * @param excursions
	 */
	public void linkHotels(List<Excursion> excursions) {
		for(int i = 0; i < excursions.size(); i++) {
			// Process the first excursion differently because it doesn't have any previous excursion
			if(i == 0) {
				Excursion firstExcursion = excursions.get(0);
				List<Site> l = firstExcursion.getSiteList();
				// If there is no sites to visit for this excursion (ex: chill at the beach) the arrival and departure hotels are the same.
				if(!l.isEmpty()) {
					Site firstSite = l.get(0);
					Hotel firstHotel = JourneyUtility.getClosestHotel(hotels, firstSite);
					firstExcursion.setDepartureHotel(firstHotel);
					
					Site lastSite = l.get(l.size()-1);
					Hotel lastHotel = JourneyUtility.getClosestHotel(hotels, lastSite);
					firstExcursion.setArrivalHotel(lastHotel);
				} else {
					Hotel hotel = hotels.get(JourneyUtility.getRandomNumber(0, hotels.size()-1));
					firstExcursion.setDepartureHotel(hotel);
					firstExcursion.setArrivalHotel(hotel);
				}
			} else {
				Excursion excursion = excursions.get(i);
				Excursion lastExcursion = excursions.get(i-1);
				excursion.setDepartureHotel(lastExcursion.getArrivalHotel());
				
				List<Site> l = excursion.getSiteList();
				if(!l.isEmpty()) {
					Site lastSite = l.get(l.size()-1);
					Hotel lastHotel = JourneyUtility.getClosestHotel(hotels, lastSite);
					excursion.setArrivalHotel(lastHotel);
				} else {
					excursion.setArrivalHotel(excursion.getDepartureHotel());
				}
			}
		}
	}
	
	/**
	 * Distributes excursions on the whole journey. When we create the excursions, depending on the frequency of visites we might have excursions
	 * with no visits at all. We distribute the excursions so as to not have all the visits during the first part of the journey and no excursions
	 * in the second part of the journey.
	 * @param excursions
	 * @return a list of excursions
	 */
	public List<Excursion> excursionDistribution(List<Excursion> excursions) {
		int priorityNumber = excursions.size();
        
		// Shuffle randomly or distribute depending on the effort level to have more even days effort wise using an average effort of activities for the day (a day = 2 excursions)
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
    
    public JourneyCritere getCritere() {
    	return critere;
    }
    
    public int getPrice() {
    	return journeyPrice;
    }
    
	public List<Excursion> getJourney() {
		return journey;
	}
    
}
