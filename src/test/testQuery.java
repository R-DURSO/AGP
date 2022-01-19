package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.lucene.queryparser.classic.ParseException;

import business.data.Hotel;
import business.data.Site;
import persistence.jdbc.JdbcPersistence;

public class testQuery {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JdbcPersistence persistence = new JdbcPersistence();
		float goodHotelPrice = persistence.luxuriousHotelMean();
		System.out.println("prix moyen hotel de luxe : "+goodHotelPrice);
		Iterator<Hotel> hotelIterator = persistence.getAllHotel(7);
		while(hotelIterator.hasNext()) {
			Hotel hotel = hotelIterator.next();
			System.out.println("Nom du site "+hotel.getName()+" "
					+ "prix de l'hotel "+hotel.getPriceLevel() +" position x :"+hotel.getPosition().getX()
					+" position y : "+hotel.getPosition().getY());
		}
	}
}