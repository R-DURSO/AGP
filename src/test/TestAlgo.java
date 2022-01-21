package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import business.core.Journey;
import business.data.Excursion;
import business.data.JourneyCritere;


public class TestAlgo {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		int confort = 1;
		int price = 3000;
		int duration = 7;
		String keyWord = "Cascades rivière";
		int frequency = 5;
		int effort = 5;
		
		JourneyCritere crit = new JourneyCritere(confort, price, duration, keyWord, frequency, effort);
		Journey journey = new Journey();
		journey.init(crit);
		
		System.out.println("====== Résultat ========");
		List<Excursion> excursions = journey.getJourney();
		for(Excursion excursion : excursions) {
			System.out.println(excursion);
		}
	}
}
