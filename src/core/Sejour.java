package core;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.And;

import business.data.Day;
import business.data.Excursion;
import business.data.SejourCritere;
import business.data.Site;
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

	public void createJourney() {
		int excursionPrice = criterion.getPrice() / 2; // a modifier pour le rendre plus intélligent
		// on va récupréer la requête des sites possible 
		ArrayList<Site> sites = null;
		if (criterion.getEffortLevel() < 5) {
			while (listExcursions.size() < 7 && excursionPrice != 0) {
				listExcursions.add(createExcursion( sites));
			}
		} else {
			while (listExcursions.size() < 14 && excursionPrice != 0) {
				listExcursions.add(createExcursion(sites));
			}
		}

	}

	public Excursion createExcursion(ArrayList<Site> site) {
		if (criterion.getEffortLevel() < 5) {

			
		} else {

		}
		return null;
	}
}
