package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;
import java.nio.file.*;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

import business.data.Boat;
import business.data.Bus;
import business.data.CheapHotel;
import business.data.Hotel;
import business.data.LuxuryHotel;
import business.data.MediumHotel;
import business.data.Position;
import business.data.Site;
import business.data.Transport;

public class JdbcPersistence  {
	
	public void dataInit() {
		System.err.println("Please don't forget to create tables manually by importing script.sql in your database !");
	}
	
	/**
	 * 
	 * This method get all mean of transport
	 * 
	 * @return an iterator with all mean of transport
	 */
	public Iterator<Transport> getAllMeanOfTransport(){
		int price;
		int comfortLevel;
		int speed;
		ArrayList <Transport> transportList =  new ArrayList<Transport>();
		Iterator<Transport> transportIterator = null;
		try {
			
			String selectTransportQuery = "SELECT prix,"
					+ "confort,vitesse FROM transport";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTransportQuery);
						
			ResultSet result = preparedStatement.executeQuery();
	
			while (result.next()) {
				
				comfortLevel = result.getInt("confort");
				price = result.getInt("prix");
				speed = result.getInt("vitesse");
				if(price == 15 && comfortLevel == 3) {
					Transport bus = new Bus(price,comfortLevel,speed);
					transportList.add(bus);
				}
				else {
					Transport boat = new Boat(price,comfortLevel,speed);
					transportList.add(boat);
				}

			}
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		transportIterator = transportList.iterator();
		return transportIterator;
	}
	/**
	 * 
	 * This method get all Station and their position
	 * 
	 * @return an iterator with the Station name and their position
	 * */
	public Iterator<NamePosStation> getStation() {
		String name;
		ArrayList <NamePosStation> namePosStationList =  new ArrayList<NamePosStation>();
		Iterator<NamePosStation> namePosStationIterator = null;
		try {
			
			String selectNamePosStationQuery = "SELECT DISTINCT nom_station, ST_X(position) as x_coordinate,"
					+ "ST_Y(position) as y_coordinate "
					+ "FROM station";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectNamePosStationQuery);
						
			ResultSet result = preparedStatement.executeQuery();
	
			
			while (result.next()) {
				Position position = new Position(0,0);
				name = result.getString("nom_station");
				position.setX(result.getFloat("x_coordinate"));
				position.setY(result.getFloat("y_coordinate"));
				NamePosStation namePosStation = new NamePosStation(name,position);
				namePosStationList.add(namePosStation);
			}
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		namePosStationIterator = namePosStationList.iterator();
		return namePosStationIterator;
	}
	
	/**
	 * 
	 * This method get all Hotel
	 * 
	 * @return an iterator with all the Hotel
	 * */
	public Iterator<Hotel> getAllHotel(int comfort) {
		int comfortLevel;
		int priceLevel;
		String name;
		ArrayList <Hotel> hotelList =  new ArrayList<Hotel>();
		Iterator<Hotel> hotelIterator = null;
		try {
			
			String selectHotelQuery = "SELECT nom_hotel, ST_X(position) as x_coordinate, ST_Y(position) as y_coordinate "
					+ ", prix, confort FROM hotel WHERE confort = ?";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectHotelQuery);
			
			preparedStatement.setInt(1,comfort);
			
			ResultSet result = preparedStatement.executeQuery();
	
			
			while (result.next()) {
				Position position = new Position(result.getFloat("x_coordinate"),result.getFloat("y_coordinate"));
				name = result.getString("nom_hotel");
				comfortLevel = result.getInt("confort");
				priceLevel = result.getInt("prix");
				if(priceLevel<100) {
					Hotel hotel = new CheapHotel(comfortLevel, priceLevel, position, name);
					hotelList.add(hotel);
				}else if(priceLevel<150) {
					Hotel hotel = new MediumHotel(comfortLevel, priceLevel, position, name);
					hotelList.add(hotel);
				}else {
					Hotel hotel = new LuxuryHotel(comfortLevel, priceLevel, position, name);
					hotelList.add(hotel);
				}
			}
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		hotelIterator = hotelList.iterator();
		return hotelIterator;
	}
	
	/**
	 * This method compute the mean price of the luxurious hotel
	 * 
	 * @return mean of the price of the luxurious hotel
	 * @throws SQLException
	 */
	public float luxuriousHotelMean() throws SQLException {
		float meanHotelPrice = 0;
		String selectluxuriousHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix >= 150 ";
		try {
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectluxuriousHotelMeanQuery);
			ResultSet result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
		} catch (SQLException se) {
			System.err.println("le message d'erreur est : "+se.getMessage());
		}
		return meanHotelPrice;
	}
	
	
	/**
	 * This method compute the mean price of the average hotel
	 * 
	 * @return mean of the price of the average hotel
	 * @throws SQLException
	 */
	public float averageHotelMean() throws SQLException {
		ResultSet result = null;
		float meanHotelPrice = 0;
		try {
			
			String selectAverageHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix >= 100 AND prix < 150";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAverageHotelMeanQuery);
			
			result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return meanHotelPrice;
	}
	
	/**
	 * This method compute the mean price of the eco hotel
	 * 
	 * @return mean of the price of the eco hotel
	 * @throws SQLException
	 */
	public float ecoHotelMean() throws SQLException {
		ResultSet result = null;
		float meanHotelPrice = 0;
		try {
			
			String selectEcoHotelMeanQuery = "SELECT AVG(prix) FROM hotel WHERE prix < 100";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectEcoHotelMeanQuery);
			
			result = preparedStatement.executeQuery();
			result.next();
			meanHotelPrice = result.getFloat(1);
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return meanHotelPrice;
	}
	
	/**
	 * 
	 * Get all Tourist Attractions who their description match the two keyword
	 * 
	 * @return an iterator with all the Tourist Attractions who their description match the two keyword
	 * @throws ParseException 
	 * @throws IOException 
	 * */
	public Iterator<SiteScore> getTouristAttractionWithKeyWord(String keyWord1,String keyWord2) throws IOException, ParseException {
		String SQLQuery = "SELECT id_site, nom_site, type_lieux, niveau_effort, "
				+ "duree_activite, prix,"
				+ "ST_X(position_site) as x_coordinate, ST_Y(position_site) as y_coordinate"
				+ " FROM site_touristique ";
		ArrayList <SiteScore> siteScoreList =  new ArrayList<SiteScore>();
		Iterator<Site> siteIteratorSQL = allTouristAttractions(SQLQuery);
		while(siteIteratorSQL.hasNext()) {
			Site site = siteIteratorSQL.next();
			Iterator<ScoreDocName> scoreIteratorTextual = luceneSearch (keyWord1,keyWord2);
			while(scoreIteratorTextual.hasNext()) {
				ScoreDocName scoreDocName = scoreIteratorTextual.next();
				String docName = scoreDocName.getDocName();
				String[] words = docName.split("\\.");
				String id_site = words[0];
				if(site.getId_site().equals(id_site)) {
					SiteScore siteScore = new SiteScore(site.getName(),site.getPrice(),site.getEffort()
							,site.getType(),site.getPos()
							,site.getDuration(),scoreDocName.getScore());
					siteScoreList.add(siteScore);
				}
			}
		}
		Iterator<SiteScore> siteScoreIterator = siteScoreList.iterator();
		siteScoreIterator = sortDescOrder(siteScoreIterator);
		return siteScoreIterator;
		
		
	}
	/**
	 * 
	 * Search with two keyword in the tourist attraction description file
	 * 
	 * @return an iterator the score and the name of the doc (in descending order)
	 * */
	private Iterator<ScoreDocName> luceneSearch (String keyWord1,String keyWord2) throws IOException, ParseException {
		int MAX_RESULTS = 100;
		ArrayList <ScoreDocName> scoreList = new ArrayList<ScoreDocName>();
		Iterator<ScoreDocName> scoreIterator = null;
		Iterator <String> iteratorIdList = getAllTouristAttractionsId();

	    Analyzer analyseur = new StandardAnalyzer();

	    Path indexpath = FileSystems.getDefault().getPath("src\\persistence\\jdbc\\site\\index");
	    Directory index = FSDirectory.open(indexpath);
	    IndexWriterConfig config = new IndexWriterConfig(analyseur);
	    IndexWriter w = new IndexWriter(index, config);
	    
	    while(iteratorIdList.hasNext()) {
	    	String fileName = iteratorIdList.next()+".txt";
		    File f = new File("src\\persistence\\jdbc\\site\\"+fileName);
	   		Document doc = new Document();
	   		doc.add(new Field("nom", f.getName(), TextField.TYPE_STORED));
	   		doc.add(new Field("contenu", new FileReader(f), TextField.TYPE_NOT_STORED));
	   		w.addDocument(doc);
	    }		
   		w.close();

	    DirectoryReader ireader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(ireader);
	    String reqstr = keyWord1 + " " + keyWord2;
	    	
	    QueryParser qp = new QueryParser("contenu", analyseur); 
	    Query req = qp.parse(reqstr);

	    TopDocs resultats = searcher.search(req, MAX_RESULTS); 

	    for(int i=0; i<resultats.scoreDocs.length; i++) {
	    	int docId = resultats.scoreDocs[i].doc;
	    	Document d = searcher.doc(docId);
	    	ScoreDocName scoreDocName = new ScoreDocName(d.get("nom"),resultats.scoreDocs[i].score);
	    	scoreList.add(scoreDocName);
	    }
	    
	    scoreIterator= scoreList.iterator(); 
	    
	    ireader.close();
	    index.deleteFile("_0.cfe");
	    index.deleteFile("_0.cfs");
	    index.deleteFile("_0.si");
	    index.deleteFile("segments_1");
	    index.deleteFile("write.lock");
	    return scoreIterator;
	  }

	/**
	 * 
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the TouristAttractions
	 * */
	private Iterator<Site> allTouristAttractions(String selectTouristAttractionsQuery) {
		String id_site;
		String name;
		int price;
		int effort;
		String type;
		int duration;
		ArrayList <Site> SiteList =  new ArrayList<Site>();
		Iterator<Site> SiteIterator = null;
		try {
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			ResultSet result = preparedStatement.executeQuery();
					
			while (result.next()) {
				Position position = new Position(result.getDouble("x_coordinate"),result.getDouble("y_coordinate"));
				id_site = result.getString("id_site");
				name = result.getString("nom_site");
				type = result.getString("type_lieux");
				effort = result.getInt("niveau_effort");
				duration = result.getInt("duree_activite");
				price = result.getInt("prix");
				Site site = new Site(id_site,name,price,effort,type,position,duration);
				SiteList.add(site);
			}
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		SiteIterator = SiteList.iterator();
		return SiteIterator;
	}
	
	/**
	 * Public version without param
	 * Get all Tourist Attractions
	 * 
	 * @return an iterator with all the TouristAttractions
	 * */
	public Iterator<Site> allTouristAttractions() {
		String id_site;
		String name;
		int price;
		int effort;
		String type;
		int duration;
		ArrayList <Site> SiteList =  new ArrayList<Site>();
		Iterator<Site> SiteIterator = null;
		try {
			String selectTouristAttractionsQuery = "SELECT id_site, nom_site, type_lieux, niveau_effort, "
					+ "duree_activite, prix,"
					+ "ST_X(position_site) as x_coordinate, ST_Y(position_site) as y_coordinate"
					+ " FROM site_touristique ";
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			ResultSet result = preparedStatement.executeQuery();
					
			while (result.next()) {
				Position position = new Position(result.getDouble("x_coordinate"),result.getDouble("y_coordinate"));
				id_site = result.getString("id_site");
				name = result.getString("nom_site");
				type = result.getString("type_lieux");
				effort = result.getInt("niveau_effort");
				duration = result.getInt("duree_activite");
				price = result.getInt("prix");
				Site site = new Site(id_site,name,price,effort,type,position,duration);
				SiteList.add(site);
			}
			
			preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		SiteIterator = SiteList.iterator();
		return SiteIterator;
	}

	/**
	 * 
	 * Get all Tourist Attractions name
	 * 
	 * @return a iterator with all the tourist attractions id
	 * */
	private Iterator <String> getAllTouristAttractionsId() {
		ArrayList <String> idList = new ArrayList<String>();
		try {
			
			String selectTouristAttractionsQuery = "SELECT id_site FROM site_touristique";
			
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(selectTouristAttractionsQuery);
			
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				idList.add(result.getString(1));
			}
			preparedStatement.close();
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		Iterator <String> iteratorIdList = idList.iterator();
		return iteratorIdList;
	}
	
	/**
	 * 
	 * @param siteScoreIterator is the iterator to sort
	 * @return an iterator of SiteScore, sort by descending order
	 */
	private Iterator<SiteScore> sortDescOrder(Iterator<SiteScore> siteScoreIterator){
		ArrayList <SiteScore> sortScoreList = new ArrayList<SiteScore>();
	      while (siteScoreIterator.hasNext()) {
	    	  sortScoreList.add(siteScoreIterator.next());
	      }
	      	
	      Collections.sort(sortScoreList, SiteScore.scoreComparator);
	      return sortScoreList.iterator();
	}
}

