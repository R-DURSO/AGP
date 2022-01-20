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
import persistence.jdbc.SiteScore;

public class testQuery {
	public static void main(String[] args) throws IOException, ParseException, SQLException{
		JdbcPersistence persistence = new JdbcPersistence();
		/*
		float goodHotelPrice = persistence.luxuriousHotelMean();
		System.out.println("prix moyen hotel de luxe : "+goodHotelPrice);
		Iterator<Hotel> hotelIterator = persistence.getAllHotel(3);
		while(hotelIterator.hasNext()) {
			Hotel hotel = hotelIterator.next();
			System.out.println("Nom de l'hotel "+hotel.getName()+" "
					+ "prix de l'hotel "+hotel.getPriceLevel() +" position x :"+hotel.getPosition().getX()
					+" position y : "+hotel.getPosition().getY());
		}
		*/
		/*
		String selectTouristAttractionsQuery = "SELECT id_site, nom_site, type_lieux, niveau_effort, "
				+ "duree_activite, prix,"
				+ "ST_X(position_site) as x_coordinate, ST_Y(position_site) as y_coordinate"
				+ " FROM site_touristique";
		Iterator<Site> siteIterator = persistence.allTouristAttractions(selectTouristAttractionsQuery);
		while(siteIterator.hasNext()) {
			Site site = siteIterator.next();
			System.out.println("\nid du site : "+site.getId_site()+ " Nom du site "+site.getName()+" "
					+ "prix du site "+site.getPrice() +" position x :"+site.getPos().getX()
					+" position y : "+site.getPos().getY());
		}
		*/
		/*
		Iterator<NamePosStation> namePosStationIterator = persistence.getStation();
		while(namePosStationIterator.hasNext()) {
			NamePosStation namePosStation = namePosStationIterator.next();
			System.out.println("\nNom de la Station "+namePosStation.getName()+" "
					+" position x :"+namePosStation.getPosition().getX()
					+" position y : "+namePosStation.getPosition().getY());
		}
		*/
		Iterator<SiteScore> siteScoreList = persistence.getTouristAttractionWithKeyWord("Cascades", "rivière");
		while(siteScoreList.hasNext()) {
			SiteScore siteScore = siteScoreList.next();
			System.out.println("score du site : "+siteScore.getScore()+ " Nom du site "+siteScore.getName()+" "
					+ "prix du site "+siteScore.getPrice() +" position x :"+siteScore.getPosition().getX()
					+" position y : "+siteScore.getPosition().getY());
		}
		
	}
}
