package core;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.And;

import business.data.Day;
import business.data.Excursion;
import business.data.Hotel;
import business.data.SejourCritere;
import business.data.Site;
import business.data.Transport;
import business.data.Visit;

public class Sejour {
	public void Sejour() {

	};

	public List<ArrayList<Visit>> getJourney() {
		return journey;
	}

	public void setJourney(List<ArrayList<Visit>> journey) {
		this.journey = journey;
	}

	private List<ArrayList<Visit>> journey = new ArrayList<ArrayList<Visit>>();
	private SejourCritere criterion;
	private int journeyLong = 7;

	private List<Visit> visit = new ArrayList<Visit>();
	private List<Day> weekDays = new ArrayList<Day>();
	private List<Excursion> listExcursions = new ArrayList<Excursion>();
	private Hotel lasHotel = null;
	private int duration = 4;
	private int effort = 0;

	public void createJourney() {
		int excursionPrice = criterion.getPrice() / 2; // a modifier pour le rendre plus intélligent
		// on va récupréer la requête des sites possible
		ArrayList<Site> sites = null;
		ArrayList<Site> sitesMVP = null;
		if (criterion.getEffortLevel() < 5) {
			while (listExcursions.size() < 7 && excursionPrice != 0) {
				duration = criterion.getDuration();
				effort = criterion.getEffortLevel();
				if (sitesMVP.size() != 0) {
					listExcursions.add(findExcursion(sitesMVP));
				} else {
					listExcursions.add(findExcursion(sites));
				}
			}
		} else {
			while (listExcursions.size() < 14 && excursionPrice != 0) {
				duration = criterion.getDuration();
				effort = criterion.getEffortLevel();
				if (sitesMVP.size() != 0) {
					listExcursions.add(findExcursion(sitesMVP));
				} else {
					listExcursions.add(findExcursion(sites));
				}
			}
		}

	}

	// @ todo prendre en compte les prix aussi + ajouter le transport

	private Excursion findExcursion(ArrayList<Site> sites) {
		Excursion bestExcursion = new Excursion();
		int index = 0;
		int newactivity = 0;
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
				lasHotel = bestExcursion.getHotelList().get(newactivity);
			} else {
				lasHotel = bestExcursion.getHotelList().get(newactivity - 1);

			}

		}

		return bestExcursion;
	}

}
