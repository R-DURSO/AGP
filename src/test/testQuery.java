package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.lucene.queryparser.classic.ParseException;

import business.data.Site;
import persistence.jdbc.JdbcPersistence;

public class testQuery {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JdbcPersistence persistence = new JdbcPersistence();
		float goodHotelPrice = persistence.luxuriousHotelMean();
		System.out.println("prix moyen hotel de luxe : "+goodHotelPrice);
		Iterator<Site> SiteIterator = persistence.allTouristAttractions();
		while(SiteIterator.hasNext()) {
			Site site = SiteIterator.next();
			System.out.println("Nom du site "+site.getName()+" "
					+ "prix du site "+site.getPrice());
		}
	}
}