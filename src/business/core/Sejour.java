package business.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.aspectj.weaver.ast.And;

import business.data.Day;
import business.data.Excursion;
import business.data.Hotel;
import business.data.SejourCritere;
import business.data.Site;
import business.data.Visit;
import persistence.jdbc.JdbcPersistence;

public class Sejour {
	private List<ArrayList<Day>> journey = new ArrayList<ArrayList<Day>>();
	private SejourCritere criterion;
	private JdbcPersistence query; 
	private int journeyLong = 7;

	private List<Visit> visit = new ArrayList<Visit>();
	private List<Day> weekDays = new ArrayList<Day>();
	private List<Excursion> listExcursions = new ArrayList<Excursion>();
	private Hotel lasHotel = null;
	private int duration = 4;
	private int effort = 0;
	private int averageHotelPrice = 0 ; 
	private int moneyRemaining = 0;
	private int numberHotelNight = 7;
	private int indexOfPriority = 0 ;
	public void Sejour() {

	};

	public List<ArrayList<Day>> getJourney() {
		return journey;
	}

	public void setJourney(List<ArrayList<Day>> journey) {
		this.journey = journey;
	}

	public List<ArrayList<Day>> createOffreJourney(){
		createJourney();
		return journey;
	}
	/** 
	 * 
	 * the findExucrsion will be use many time for create a list of excursion
	 * selectday will fill the 7 doy the journey
	 * create journey will storing on the week
	 */
	public void createJourney() {
		// ajout la quête pour le prix moyen de l'hotel 
		moneyRemaining = criterion.getPrice() - 15 - numberHotelNight * averageHotelPrice; // a modifier pour le rendre plus intélligent
		// on va récupréer la requete des sites possible
		ArrayList<Site> sites = null;
		ArrayList<Site> sitesMVP = null;
		if (criterion.getEffortLevel() < 5) {
			while (listExcursions.size() < 7 && moneyRemaining != 0) {
				duration = criterion.getDuration();
				effort = criterion.getEffortLevel();
				if (sitesMVP.size() != 0) {
					listExcursions.add(findExcursion(sitesMVP));
					indexOfPriority = listExcursions.size();
					
				} else {
					listExcursions.add(findExcursion(sites));
				}
			}
			for (int r = listExcursions.size(); r<15 ; r++) {
				listExcursions.add(new Excursion(0,lasHotel.getComfortLevel(),null,lasHotel,lasHotel,null));
			}
		} else {
			while (listExcursions.size() < 14 && moneyRemaining != 0) {
				duration = criterion.getDuration();
				effort = criterion.getEffortLevel();
				if (sitesMVP.size() != 0) {
					listExcursions.add(findExcursion(sitesMVP));
					indexOfPriority = listExcursions.size();
					} else {
					listExcursions.add(findExcursion(sites));
				}
			}
		}

		creationWorkingTime();
	}

	// @ todo prendre en compte les prix aussi + ajouter le transport
	/**
	 * 
	 * @param sites  a list of different possible to go during this journey 
	 * @return a best 
	 */
	private Excursion findExcursion(ArrayList<Site> sites) {
		Excursion bestExcursion = new Excursion();
		int index = 0;
		int newactivity = 0;
		while (sites.size() != 0) {
			if (duration - sites.get(index).getDuration() >= 0 && effort - sites.get(index).getEffort() >= 0) {
				bestExcursion.addSite(sites.get(index));
				duration = duration - sites.get(index).getDuration();
				effort = effort - sites.get(index).getEffort();
				newactivity++;
				sites.remove(index);
			} else {
				index++;
			}
			if (duration <= 0 || effort <= 0) {
				if (lasHotel != null) {
					lasHotel = bestExcursion.getArrivalHotel();
					return bestExcursion;
				} else {
					lasHotel = bestExcursion.getDepartureHotel();
					return bestExcursion;
				}

			}
		}
		bestExcursion = new Excursion(0,lasHotel.getComfortLevel(),null,lasHotel,lasHotel,null) ;
		return bestExcursion; 
	}
	/**
	 * repartion of the different excursion possible 
	 */
public void creationWorkingTime(){
	while(listExcursions.size() != 0 ) {
		int i = 0 ;
		if(effort  <=5) {
			for(Day days : weekDays ) {
					days.add(listExcursions.get(i));
					listExcursions.remove(i);
			}
		}else {
			for(Day days : weekDays ) {
				i = Math.subtractExact(0, listExcursions.size());
				days.add(listExcursions.get(i));
				listExcursions.remove(i);
				i = Math.subtractExact(0, listExcursions.size());
				days.add(listExcursions.get(i));
				listExcursions.remove(i);
		}
		}
	}
	journey.add((ArrayList<Day>) weekDays);
}
}
