package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import business.core.Journey;
import business.data.Day;
import business.data.JourneyCritere;


public class TestAlgo {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JourneyCritere crit = new JourneyCritere(1, 3000, 7, "test", 5, 5);
		Journey expJourney = new Journey(crit);
		//System.out.println(expJourney.getCritere().getEffortLevel());
		expJourney.createJourney();
		List<Day>  days = expJourney.getWeek();
		System.out.println(days.size());
		for (Day day : days) {
			System.out.println(day);
		}

}
}
