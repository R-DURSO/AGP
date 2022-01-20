package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.lucene.queryparser.classic.ParseException;

import business.data.Hotel;
import business.data.Site;
import persistence.jdbc.JdbcPersistence;
import persistence.jdbc.NamePosStation;

public class testQuery {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JdbcPersistence persistence = new JdbcPersistence();
		float goodHotelPrice = persistence.luxuriousHotelMean();
		System.out.println("prix moyen hotel de luxe : "+goodHotelPrice);
		Iterator<Hotel> hotelIterator = persistence.getAllHotel(7);
		while(hotelIterator.hasNext()) {
			Hotel hotel = hotelIterator.next();
			System.out.println("Nom de l'hotel "+hotel.getName()+" "
					+ "prix de l'hotel "+hotel.getPriceLevel() +" position x :"+hotel.getPosition().getX()
					+" position y : "+hotel.getPosition().getY());
		}
		/*
		Iterator<Site> siteIterator = persistence.allTouristAttractions();
		while(siteIterator.hasNext()) {
			Site site = siteIterator.next();
			System.out.println("\nid du site : "+site.getId_site()+ " Nom du site "+site.getName()+" "
					+ "prix du site "+site.getPrice() +" position x :"+site.getPos().getX()
					+" position y : "+site.getPos().getY());
		}
		*/
		Iterator<NamePosStation> namePosStationIterator = persistence.getStation();
		while(namePosStationIterator.hasNext()) {
			NamePosStation namePosStation = namePosStationIterator.next();
			System.out.println("\nNom de la Station "+namePosStation.getName()+" "
					+" position x :"+namePosStation.getPosition().getX()
					+" position y : "+namePosStation.getPosition().getY());
		}
	}
}
