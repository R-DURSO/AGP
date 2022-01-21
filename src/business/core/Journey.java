package business.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.aspectj.weaver.patterns.IScope;
import org.primefaces.expression.impl.ThisExpressionResolver;

import business.data.Day;
import business.data.Excursion;
import business.data.Hotel;
import business.data.JourneyCritere;
import business.data.Site;
import business.data.Transport;
import persistence.jdbc.JdbcPersistence;
import persistence.jdbc.SiteScore;

public class Journey {

	JdbcPersistence persistence = new JdbcPersistence();
	JourneyCritere critere ;
	private final int EXCURSION_DURATION = 4;
	private List<Excursion> journey = new ArrayList<>();
	
	// journeyDuration -> half-day
	private int journeyDuration = 14;
	private int journeyPrice = 0;
	private int budget;
	private int confort;
	private int frequency;
	private int effort;
	private String keyWord;
	
	// Iterators
	private Iterator<Hotel> hotelIterator;
	private Iterator<Transport> transportIterator;
	private Iterator<SiteScore> siteIterator;	
	private Iterator<Site> otherSiteIterator;
	
	// Lists
	// Contains hotels corresponding to the user's preferences
	private List<Hotel> hotels;
	// Contains sites corresponding to the user's preferences
	private List<Site> sites;
	// Contains sites that DOES NOT correspond to the user's preferences
	private List<Site> otherSites;
	// Contains the different day of the journey week 
	private List<Day> days;
	
	public Journey(JourneyCritere critere) {
		this.critere = critere;
		this.budget = critere.getPrice();
		this.confort = critere.getConfortChoice();
		this.frequency = critere.getFrequency();
		this.effort = critere.getEffortLevel();
	}
	public Journey() {
	}
	
	public void init(JourneyCritere critere) {
		this.critere = critere;
		this.budget = critere.getPrice();
		this.confort = critere.getConfortChoice();
		this.frequency = critere.getFrequency();
		this.effort = critere.getEffortLevel();
		//this.keyWord = critere.getKeyWord();
		this.keyWord = "Cascades rivière";
		System.out.println("************ Critere ************");
		System.out.println(critere.getPrice());
		System.out.println(critere.getConfortChoice());
		System.out.println(critere.getFrequency());
		System.out.println(critere.getEffortLevel());
		System.out.println(critere.getKeyWord());
		
		String[] words = keyWord.split(" ");
		System.out.println("Words = " + words[0] + "|" + words[1]);
		
		siteIterator = persistence.getTouristAttractionWithKeyWord(words[0], words[1]);
		otherSiteIterator = persistence.allTouristAttractions();
		hotelIterator = persistence.getAllHotel(confort);
		hotels = new ArrayList<>();
		sites = new ArrayList<>();
		otherSites = new ArrayList<>();
		days = new ArrayList<>();
		
		hotelIterator.forEachRemaining(hotels::add);
		siteIterator.forEachRemaining(sites::add);
		otherSiteIterator.forEachRemaining(otherSites::add);
		
//		System.out.println("==========  Hotels ================");
//		for(Hotel hotel : hotels) {
//			System.out.println(hotel.getName());
//		}
//		
		System.out.println("==========  Sites ================");
		for(Site site : sites) {
			System.out.println(site.getName());
		}

		System.out.println("==========  Other Sites ================");
		for(Site site : otherSites) {
			System.out.println(site.getName());
		}
		
		System.out.println("==================================");
		
		createJourney();
	}

	public void createJourney() {
		// on réparti les excursion de facon aléatoire 
		Collections.shuffle(otherSites);
		
		// Collections.shuffle(siteList);
		
		// Create excursions with visits
		List<Excursion> excursions = new ArrayList<Excursion>();
		// 0 < frequency/10 < 1 
		double frequencyFactor = (double) frequency / 10;
		int nbExcursions = (int) Math.round(frequencyFactor * journeyDuration);
//		double freq = 5 / 10.0;
//		int nbExcursions =    (int) (freq* journeyDuration);
		for(int i = 0; i < nbExcursions; i++) {
			excursions.add(createExcursion());
			System.out.println("++++++++++++++++++++++++++++++++++++");
		}
		System.out.println("========= Create =========");
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
		
		// Create the remaining empty excursions to complete the journey
		int nbEmptyExcursions = journeyDuration - excursions.size();
		for (int i = 0; i < nbEmptyExcursions; i++) {
            excursions.add(new Excursion());
        }
		System.out.println("========= Empty =========");
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
		
		// Distribute the excursions and empty excursions "randomly"
		excursions = excursionDistribution(excursions);
		System.out.println("========= Distribution =========");
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
		
		// Add hotels
		linkHotels(excursions);
		
		// Add transports
		linkTransports(excursions);
		
		System.out.println("========= Fin =========");
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
		System.out.println("===================================");
		
		journey = excursions;
		
		days = createWeek(excursions);
		
		System.out.println("Price = " + journeyPrice);
	}
		
	public Excursion createExcursion() {
		Excursion excursion = new Excursion();
		int totalExcursionTime = 0;
		List<Site> visitedSites = new ArrayList<>();
		
		if(!sites.isEmpty()) {
			for(Site site : sites) {
				System.out.println("Site: " + site.getName() + " " + site.getPrice() + " " + site.getDuration());
				if(totalExcursionTime + site.getDuration() <= EXCURSION_DURATION && journeyPrice + site.getPrice() <= budget) {
					excursion.addSite(site);
					journeyPrice += site.getPrice();
					totalExcursionTime += site.getDuration();
					visitedSites.add(site);
					System.out.println("added");
				}
				System.out.println("");
			}
		}
		
		// Complete the excursion with other sites if there is still some time left
		for(Site site : otherSites) {
			System.out.println("other");
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
		
		// closest station from departure
		// closest station from sites
		// closest station from arrival
		
		return excursion;
	}
	
	public void linkTransports(List<Excursion> excursions) {
		for(Excursion excursion : excursions) {
			
		}
	}
	
	// Set departure and arrival hotels for each excursion
	public void linkHotels(List<Excursion> excursions) {
		for(int i = 0; i < excursions.size(); i++) {
			if(i == 0) {
				Excursion firstExcursion = excursions.get(0);
				List<Site> l = firstExcursion.getSiteList();
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
	
	public List<Excursion> excursionDistribution(List<Excursion> excursions) {
		int priorityNumber = excursions.size();
        
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
        
        for (int index = 0 ; index < journeyDuration; index +=2) {
            Day newDay = new Day();
            newDay.setMorning(excursions.get(index));
            newDay.setAfternoon(excursions.get(index+1));
            newDay.updatePrice();
            newDay.updateComfortLevel();
            week.add(newDay);
        }
        return week;
    }
    
    public List<Day> getWeek(){
    	return days;
    }
    
    public JourneyCritere getCritere() {
    	return critere;
    }
    
    public void setCritere(JourneyCritere critere) {
    	this.critere = critere;
		this.budget = critere.getPrice();
		this.confort = critere.getConfortChoice();
		this.frequency = critere.getFrequency();
		this.effort = critere.getEffortLevel();
    }
    
    public int getPrice() {
    	return journeyPrice;
    }
    
	public List<Excursion> getJourney() {
		return journey;
	}
    
}
