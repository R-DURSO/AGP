package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import business.core.Journey;
import business.data.Day;
import business.data.Excursion;
import business.data.JourneyCritere;


public class TestAlgo {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JourneyCritere crit = new JourneyCritere(1, 3000, 7, "Cascades rivière", 5, 5);
		Journey journey = new Journey();
		//journey.setCritere(crit);
		//System.out.println(expJourney.getCritere().getEffortLevel());
		journey.init(crit);
//		List<Day>  days = journey.getWeek();
//		System.out.println(days.size());
//		for (Day day : days) {
//			System.out.println(day);
//		}
//		System.out.println((int)0.9*14);
		
		System.out.println("====== Résultat ========");
		List<Excursion> excursions = journey.getJourney();
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
	}
}
